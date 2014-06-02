package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.copyright._
import org.joda.time.DateTime
import views.html
import utils.{JournalUtilProvider, TokenGenerator, PdfGenerator}
import utils.MailSender.{Mail, send}

import utils.MailSender.send
import utils.MailSender.Mail
import models.copyright.Copyright
import models.copyright.Contribution
import models.copyright.CopyrightTransferRequest
import models.copyright.CorrespondingAuthor
import models.dao.{CopyrightTransferOjsDao, CopyrightTransferInternalDao}
import play.api.mvc.Results._
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

  def consent = Action {
    implicit request => {
      val id = request.body.asFormUrlEncoded.get("article-id").apply(0).toInt
      if (!CopyrightTransferOjsDao.articleExists(id)) {
        BadRequest(views.html.errors.badRequest("The article #" + id + " does not exist!"))
      } else if (CopyrightTransferInternalDao.transferExists(id)) {
        BadRequest(views.html.errors.badRequest("Copyright has already been transferred for the article #" + id + "!"))
      } else {
        val copyright = getPaperDataById(id)
        val journalId = CopyrightTransferOjsDao.getJournalIDForArticle(id)
        Ok(html.copyright.consentForm(form.fill(copyright), journalId))
      }
    }
  }

  def getPaperDataById(id: Int): Copyright = {
    CopyrightTransferOjsDao.getAuthorsForArticle(id)
  }

  def submit = Action {
    implicit request =>
      form.bindFromRequest.fold(
        errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again."),
        cd => {
          val copyrightTransferRequest = CopyrightTransferRequest(None, cd, DateTime.now(), request.remoteAddress, CopyrightTransferStatus.UNCONFIRMED)
          filledConsents += cd.ojsId -> copyrightTransferRequest
          Ok(html.copyright.summary(copyrightTransferRequest))
        }
      )
  }

  def confirm(id: Int) = Action {
    implicit request =>
      val copyrightTransferRequest = filledConsents.get(id).get
      filledConsents -= id
      CopyrightTransferInternalDao.saveTransfer(copyrightTransferRequest)

      val toEmail = copyrightTransferRequest.copyrightData.correspondingAuthor.email

      val pdfFile = java.io.File.createTempFile("CopyrightTransferForm", ".pdf")
      PdfGenerator.generate(copyrightTransferRequest, pdfFile, CopyrightTransferOjsDao.getJournalIDForArticle(id))
        send a new Mail(
          from = ("test@slonka.udl.pl", "Journal Manager"),
          to = List(toEmail),
          subject = "[Journal Manager] Please confirm the copyright transfer",
          message = "This is the Journal Manager system.\n" +
            "Your e-mail address was used to fill a copyright transfer form. Details of the transfer can be found in the attached PDF file.\n" +
            "Please confirm the copyright transfer by clicking the link below:\n" +
            "http://" + request.host + "/confirm/" + TokenGenerator.generateAndSave(toEmail) + "\n" +
            "If you didn't fill the copyright transfer form, please ignore this message.\n",
          attachment = Option(pdfFile)
        )
        pdfFile.delete()
        Ok(html.copyright.confirmation(toEmail))
  }

  def verify(token: String) = Action {
    implicit request => {
      val tokenSHA = TokenGenerator.toSHA(token)
      val verificationResult = CopyrightTransferInternalDao.markTransferAsConfirmed(tokenSHA) != 0
      Ok(html.copyright.verify(verificationResult))
    }

  }
}
