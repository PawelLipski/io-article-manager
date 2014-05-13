package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.copyright._
import org.joda.time.DateTime
import views.html
import utils.{TokenGenerator, PdfGenerator}
import utils.MailSender.send
import utils.MailSender.Mail
import models.copyright.Copyright
import models.copyright.Contribution
import models.copyright.CopyrightTransferRequest
import models.copyright.CorrespondingAuthor

object CopyrightTransfer extends Controller {

  var copyrightTransferRequest: CopyrightTransferRequest = null

  val form: Form[Copyright] = Form(
    mapping(
      "ojsId" -> number,
      "title" -> text,
      "correspondingAuthor" -> mapping(
        "name" -> text,
        "affiliation" -> text,
        "email" -> text
      )(CorrespondingAuthor.apply)(CorrespondingAuthor.unapply),
      "contribution" -> list(mapping(
        "authorName" -> text,
        "affiliation" -> text,
        "contribution" -> text,
        "percent" -> number
      )(Contribution.apply)(Contribution.unapply)),
      "financial" -> text
    )(Copyright.apply)(Copyright.unapply)
  )

  def index = Action {
    val consentText = scala.io.Source.fromFile("./public/resources/Computer_Science_ctp.txt").getLines().toList
    Ok(html.copyright.consent(form, consentText))
  }

  def submit = Action {
    implicit request =>
      form.bindFromRequest.fold(
        errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again."),
        cd => {
          copyrightTransferRequest = CopyrightTransferRequest(None, cd, DateTime.now(), request.remoteAddress, CopyrightTransferStatus.UNCONFIRMED)
          Ok(html.copyright.summary(copyrightTransferRequest, form.fill(cd)))
        }
      )
  }

  def confirm = Action { request =>
    val pdfFile = java.io.File.createTempFile("CopyrightTransferForm", ".pdf")
    val toEmail = copyrightTransferRequest.copyrightData.correspondingAuthor.email
    PdfGenerator.generate(copyrightTransferRequest, pdfFile)
    send a new Mail(
      from = ("test@slonka.udl.pl", "Journal Manager"),
      to = List(toEmail),
      subject = "[Journal Manager] Please confirm the copyright transfer",
      message = "This is the Journal Manager system.\n" +
        "Your e-mail address was used to fill a copyright transfer form. Details of the transfer can be found in the attached PDF file.\n" +
        "Please confirm the copyright transfer by clicking the link below:\n" +
        "http://" +  request.host + "/confirm/" + TokenGenerator.generateAndSave(toEmail) + "\n" +
        "If you didn't fill the copyright transfer form, please ignore this message.\n",
      attachment = Option(pdfFile)
    )
    pdfFile.delete()
    Ok(html.copyright.confirmation(toEmail))
  }

}
