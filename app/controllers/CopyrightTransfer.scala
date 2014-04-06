package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import utils.PdfGenerationDemo

import views._
import models.CopyrightData

/**
 * Created by Zeuko on 05.04.14.
 */
object CopyrightTransfer extends Controller {

  var copyrightData: CopyrightData = CopyrightData("", "", "", "", "");

  val form: Form[CopyrightData] = Form(
    mapping(
      "title" -> text,
      "authors" -> text,
      "correspondingAuthor" -> text,
      "contribution" -> text,
      "financial" -> text
    )(CopyrightData.apply)(CopyrightData.unapply)
  )

  def index = Action {
    Ok(html.copyright.consent(form))
  }

  def submit = Action { implicit request =>
    form.bindFromRequest.fold(
      errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again."),
      cd =>  {
        copyrightData = cd      // temporarily => received data will be stored separately and searched by id, i think
        Ok(html.copyright.summary(cd, form.fill(cd)))
      }
    )
  }

  def generateDoc = Action {
      Ok(PdfGenerationDemo.generate(copyrightData.getAsFormattedString())).as("application/pdf")
  }
}
