package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.copyright._
import views.html
import utils.{TokenGenerator, PdfGenerator, ErrorWrapper}

import utils.MailSender.send
import dao.{CopyrightTransferOjsDao, CopyrightTransferInternalDao}
import models.copyright.Contribution
import models.copyright.CopyrightTransferRequest
import models.copyright.CorrespondingAuthor
import utils.MailSender.Mail

object CopyrightTransfer extends Controller {

  var filledConsents: Map[Int, CopyrightTransferRequest] = Map()

  val form: Form[CopyrightWrapper] = Form(
    mapping(
      "ojsId" -> number,
      "title" -> text,
      "financial" -> text,
      "correspondingAuthor" -> mapping(
        "firstName" -> text,
        "middleName" -> optional(text),
        "lastName" -> text,
        "affiliation" -> text,
        "email" -> text
      )(CorrespondingAuthor.assemble)(CorrespondingAuthor.unassemble),
      "contribution" -> list(mapping(
        "firstName" -> text,
        "middleName" -> optional(text),
        "lastName" -> text,
        "affiliation" -> text,
        "contribution" -> text,
        "percent" -> number
      )(Contribution.assemble)(Contribution.unassemble))
    )(CopyrightWrapper.assemble)(CopyrightWrapper.unassemble)
  )

  def index = Action {
    implicit request =>
      Ok(html.copyright.index())
  }

  def proceedToConsent(ojsArticleId: Int)(implicit request: Request[AnyContent]) = {

    if (!CopyrightTransferOjsDao.articleExists(ojsArticleId)) {
      BadRequest(views.html.errors.badRequest("The article #" + ojsArticleId + " does not exist!"))

    } else if (CopyrightTransferInternalDao.verifiedTransferRequestExists(ojsArticleId)) {
      BadRequest(views.html.errors.badRequest("Copyright has already been transferred for the article #" + ojsArticleId + "!"))

    } else {
      val copyrightFormWrapper = CopyrightTransferOjsDao.getCopyrightFormWrapperForArticle(ojsArticleId)
      val journalId = CopyrightTransferOjsDao.getJournalIDForArticle(ojsArticleId)
      Ok(html.copyright.consentForm(form.fill(copyrightFormWrapper), journalId))
    }
  }

  def consentWithIdInUrl(ojsArticleId: Int) = Action {
    implicit request => proceedToConsent(ojsArticleId)
  }

  def consentWithIdInQueryString = Action {
    implicit request => {
      var ok = true
      var ojsArticleId = 0
      try {
        ojsArticleId = request.getQueryString("article-id").get.toInt
      } catch {
        case e: Exception => ok = false
      }
      if (ok)
        proceedToConsent(ojsArticleId)
      else
        BadRequest(html.errors.badRequest("The provided URL is malformed. Please try again."))
    }
  }

  def submit = Action {
    implicit request =>
      form.bindFromRequest.fold(
        ErrorWrapper.getFormErrorWrapper[CopyrightWrapper],
        wrapper => {
          val ipAddress = request.remoteAddress
          val transferId = CopyrightTransferInternalDao.submitTransferRequestAndReturnId(
            wrapper.contributionList, wrapper.correspondingAuthor, wrapper.copyright, ipAddress)
          Ok(html.copyright.summary(wrapper)).withCookies(Cookie("transferId", transferId.toString))
        }
      )
  }

  def confirm = Action {
    implicit request =>

      val transferId = request.cookies.get("transferId").get.value.toInt
      val token = CopyrightTransferInternalDao.confirmTransferRequestAndReturnToken(transferId)
      val transferRequest: CopyrightTransferRequestWrapper = CopyrightTransferInternalDao.fetchTransferRequest(transferId)
      val verificationLink = "http://" + request.host + "/copyright-transfer/verify/" + token

      val email = transferRequest.correspondingAuthor.email

      val pdfFile = java.io.File.createTempFile("CopyrightTransferForm", ".pdf")
      val ojsJournalId = CopyrightTransferOjsDao.getJournalIDForArticle(transferRequest.copyright.ojsArticleId)
      PdfGenerator.generate(transferRequest, pdfFile, ojsJournalId)
      send a new Mail(
        from = ("test@slonka.udl.pl", "Journal Manager"),
        to = List(email),
        subject = "[Journal Manager] Please confirm the copyright transfer",
        message = "This is the Journal Manager system.\n" +
          "Your e-mail address was used to fill a copyright transfer form. Details of the transfer can be found in the attached PDF file.\n" +
          "Please confirm the copyright transfer by clicking the link below:\n" +
          verificationLink + "\n" +
          "If you didn't fill the copyright transfer form, please ignore this message.\n",
        attachment = Option(pdfFile)
      )
      pdfFile.delete()

      Ok(html.copyright.confirmation(email))
  }

  def verify(token: String) = Action {
    implicit request => {
      val tokenSHA = TokenGenerator.toSha(token)
      val verificationResult = CopyrightTransferInternalDao.verifyTransferRequest(tokenSHA) != 0
      Ok(html.copyright.verify(verificationResult))
    }

  }
}
