package controllers

import play.api.mvc._

object Application extends Controller {

  def index = Action {
    implicit request =>
      Ok(views.html.index())
  }

  def contact = Action {
    implicit request =>
      Ok(views.html.contact())
  }

}
