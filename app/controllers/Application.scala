package controllers

import play.api.mvc._
import models.dao.CopyrightTransferInternalDao

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
