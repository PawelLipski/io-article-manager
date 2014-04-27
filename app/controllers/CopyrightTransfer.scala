package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.copyright.{CorrespondingAuthor, Contribution, Copyright}
import org.joda.time.DateTime
import views.html
import utils.{TokenGenerator, PdfGenerator}
import utils.MailSender.{Mail, send}
import java.io.File

object CopyrightTransfer extends Controller {

  var copyrightData: Copyright = null
  var contribution: List[Contribution] = List.empty
  var dateFilled: DateTime = null
  var ipAddress: String = null

  val form: Form[Copyright] = Form(
    mapping(
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

    val consentText = scala.io.Source.fromFile(".\\public\\resources\\Computer_Science_ctp.txt").getLines().toList

    //println(new File(".").getAbsolutePath())
    Ok(html.copyright.consent(form, consentText))
  }

  def submit = Action {
    implicit request =>
      form.bindFromRequest.fold(
        errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again."),
        cd => {
          copyrightData = cd
          dateFilled = DateTime.now()
          ipAddress = request.remoteAddress
          Ok(html.copyright.summary(cd, dateFilled, ipAddress, form.fill(cd)))
        }
      )
  }

  def confirm = Action {
    val pdfFile = java.io.File.createTempFile("CopyrightTransferForm", ".pdf")
    PdfGenerator.generate(copyrightData, dateFilled, ipAddress, pdfFile)
    send a new Mail(
      from = ("test@slonka.udl.pl", "Journal Manager"),
      to = List(copyrightData.correspondingAuthor.email),
      subject = "[Journal Manager] Please confirm the copyright transfer",
      message = "This is the Journal Manager system.\n" +
        "Your e-mail address was used to fill a copyright transfer form. Details of the transfer can be found in the attached PDF file.\n" +
        "Please confirm the copyright transfer by clicking the link below:\n" +
        "http://localhost:9000/confirm/" + TokenGenerator.generateAndSave(copyrightData.correspondingAuthor.email) + "\n" +
        "If you didn't fill the copyright transfer form, please ignore this message.\n",
      attachment = Option(pdfFile)
    )
    pdfFile.delete()
    Ok("A confirmation e-mail has been sent to " + copyrightData.correspondingAuthor.email + ". Please check your mailbox.")
  }

}
