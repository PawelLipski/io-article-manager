package controllers

import play.api._
import play.api.mvc._
import utils.PdfGenerator
import java.util.Date

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready. So let's start coding, John Doe!"))
  }

  def pdfGenerationDemo = Action {
    Ok(PdfGenerator.generate("Hello PDF!\n" + new Date().toString)).as("application/pdf")
  }

}