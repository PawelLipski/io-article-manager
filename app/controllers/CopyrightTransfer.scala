package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import utils.PdfGenerationDemo

import views._
import models.copyright.{Copyright, Contribution, CorrespondingAuthor}

/**
 * Created by Zeuko on 05.04.14.
 */
object CopyrightTransfer extends Controller {

  var copyrightData: Copyright = null;
  var contribution: List[Contribution] = List.empty;

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
    Ok(html.copyright.consent(form))
  }

  def submit = Action { implicit request =>
    form.bindFromRequest.fold(
      errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again."),
      cd =>  {
        copyrightData = cd
        Ok(html.copyright.summary(cd, form.fill(cd)))
      }
    )
  }

  def generateDoc = Action {
    Ok(PdfGenerationDemo.generate(copyrightData.getAsFormattedString())).as("application/pdf")
  }


}
