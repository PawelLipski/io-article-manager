package controllers

import play.api._
import play.api.mvc._
import play.api.data._

object Confidential extends Controller with Secured {

  def index = withAuth {
    username => implicit request =>
      Ok("Hello " + username)
  }

}
