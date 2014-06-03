package controllers

import play.api.mvc._
import models.dao.GeneralOjsDao

object Application extends Controller {

  def index = Action {
    implicit request =>
      Ok(views.html.index())

  }

}
