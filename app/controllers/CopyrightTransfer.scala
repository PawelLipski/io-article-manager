package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.copyright._
import org.joda.time.DateTime
import views.html
import utils.{TokenGenerator, PdfGenerator, ErrorWrapper}

import utils.MailSender.send
import models.dao.{CopyrightTransferOjsDao, CopyrightTransferInternalDao}
import models.copyright.Copyright
import models.copyright.Contribution
import models.copyright.CopyrightTransferRequest
import models.copyright.CorrespondingAuthor
import utils.MailSender.Mail

object CopyrightTransfer extends Controller {

  var filledConsents: Map[Int, CopyrightTransferRequest] = Map()

  val form: Form[Copyright] = Form(
    mapping(
      "ojsId" -> number,
      "title" -> text,
      "correspondingAuthor" -> mapping(
        "firstName" -> text,
        "middleName" -> optional(text),
        "lastName" -> text,
        "affiliation" -> text,
        "email" -> text
      )(CorrespondingAuthor.apply)(CorrespondingAuthor.unapply),
      "contribution" -> list(mapping(
        "firstName" -> text,
        "middleName" -> optional(text),
        "lastName" -> text,
        "affiliation" -> text,
        "contribution" -> text,
        "percent" -> number
      )(Contribution.apply)(Contribution.unapply)),
      "financial" -> text
    )(Copyright.apply)(Copyright.unapply)
  )

  def index = Action {
    implicit request =>
      Ok(html.copyright.index())
  }

  def proceedToConsent(id: Int)(implicit request: Request[AnyContent]) = {
    if (!CopyrightTransferOjsDao.articleExists(id)) {
      BadRequest(views.html.errors.badRequest("The article #" + id + " does not exist!"))
    } else if (CopyrightTransferInternalDao.confirmedTransferExists(id)) {
      BadRequest(views.html.errors.badRequest("Copyright has already been transferred for the article #" + id + "!"))
    } else {
      val copyright = getPaperDataById(id)
      val journalId = CopyrightTransferOjsDao.getJournalIDForArticle(id)
      Ok(html.copyright.consentForm(form.fill(copyright), journalId))
    }
  }

  def consentWithIdInUrl(id: Int) = Action {
    implicit request => proceedToConsent(id)
  }

  def consentWithIdInQueryString = Action {
    implicit request => {
      var ok = true
      var id = 0
      try {
        id = request.getQueryString("article-id").get.toInt
      } catch {
        case e: Exception => ok = false
      }
      if (ok)
        proceedToConsent(id)
      else
        BadRequest(html.errors.badRequest("The provided URL is malformed. Please try again."))
    }
  }

  def getPaperDataById(id: Int): Copyright = {
    CopyrightTransferOjsDao.getAuthorsForArticle(id)
  }

  def submit = Action {
    implicit request =>
      form.bindFromRequest.fold(
        ErrorWrapper.getFormErrorWrapper[Copyright],
        cd => {
          val copyrightTransferRequest = CopyrightTransferRequest(None, cd, DateTime.now(), request.remoteAddress, CopyrightTransferStatus.UNCONFIRMED)
          filledConsents += cd.ojsId -> copyrightTransferRequest
          Ok(html.copyright.summary(copyrightTransferRequest, form.fill(cd)))
        }
      )
  }

  def confirm(ojsId: Int) = Action {
    implicit request =>
      val copyrightTransferRequest = filledConsents.get(ojsId).get
      filledConsents -= ojsId

      val token = CopyrightTransferInternalDao.saveTransferAndReturnTheToken(copyrightTransferRequest)
      val verificationLink = "http://" + request.host + "/confirm/" + token

      val toEmail = copyrightTransferRequest.copyrightData.correspondingAuthor.email

      val pdfFile = java.io.File.createTempFile("CopyrightTransferForm", ".pdf")
      PdfGenerator.generate(copyrightTransferRequest, pdfFile, CopyrightTransferOjsDao.getJournalIDForArticle(ojsId))
      send a new Mail(
        from = ("test@slonka.udl.pl", "Journal Manager"),
        to = List(toEmail),
        subject = "[Journal Manager] Please confirm the copyright transfer",
        message = "This is the Journal Manager system.\n" +
          "Your e-mail address was used to fill a copyright transfer form. Details of the transfer can be found in the attached PDF file.\n" +
          "Please confirm the copyright transfer by clicking the link below:\n" +
          verificationLink + "\n" +
          "If you didn't fill the copyright transfer form, please ignore this message.\n",
        attachment = Option(pdfFile)
      )
      pdfFile.delete()
      Ok(html.copyright.confirmation(toEmail))
  }

  def verify(token: String) = Action {
    implicit request => {
      val tokenSHA = TokenGenerator.toSha(token)
      val verificationResult = CopyrightTransferInternalDao.markTransferAsConfirmed(tokenSHA) != 0
      Ok(html.copyright.verify(verificationResult))
    }

  }
}
