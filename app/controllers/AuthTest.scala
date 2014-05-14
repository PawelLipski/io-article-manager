package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

trait Secured {
  self: Controller =>

  /**
   * Retrieve the connected user id.
   */
  def username(request: RequestHeader) = request.session.get("user")

  /**
   * Redirect to login if the use in not authorized.
   */
  def onUnauthorized(request: RequestHeader): SimpleResult

  def IsAuthenticated(f: => String => Request[AnyContent] => Result) =
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
}

object AuthTest extends Controller with Secured {

  lazy val loginForm = Form(
    tuple(
      "user" -> text,
      "password" -> text
    ) verifying ("Invalid user or password", result => result match {
      case (user, password) if user == "user" && password == "password" => true
      case _ => false
    })
  )

  def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.AuthTest.login())

  def index = IsAuthenticated { username => implicit request =>
    //Ok(views.html.index("You have been successfully authenticated"))
    Ok(views.html.index())
  }

  def doLogin = Action { implicit request =>
    Logger.info("Authenticating user")
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors, routes.AuthTest.login)),
      user => Redirect(routes.Application.index).withSession("user" -> user._1)
    )
  }

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm, routes.AuthTest.doLogin))
  }

  def logout = Action {
    Redirect(routes.AuthTest.login).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
}