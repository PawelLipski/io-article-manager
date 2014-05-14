package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.dao.AuthenticationDao
import org.apache.commons.codec.binary.Hex

trait Secured {
  self: Controller =>

  /**
   * Retrieve the connected user id.
   */
  def username(request: RequestHeader) = request.session.get("user")

  /**
   * Redirect to login if the use in not authorized.
   */
  def onUnauthorized(request: RequestHeader): SimpleResult =
    Results.Redirect(routes.AuthenticationController.login()).withSession(("returnUrl", request.path))


  def withAuth(f: => String => Request[AnyContent] => Result) =
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
}

object AuthenticationController extends Controller with Secured {

  lazy val loginForm = Form(
    tuple(
      "user" -> text,
      "password" -> text
    ) verifying ("Invalid user or password", result => result match {
      case (user, password) if verifyCredentials(user, password) => true
      case _ => false
    })
  )

  private def verifyCredentials(user: String, password: String): Boolean = {
    val sha1 = java.security.MessageDigest.getInstance("SHA-1")
    val actualSha1Sum = new String(Hex.encodeHex(sha1.digest(password.getBytes)))
    println(actualSha1Sum)
    val expectedSha1Sum = AuthenticationDao.getPasswordSha1SumForUser(user)
    println(expectedSha1Sum)
    actualSha1Sum == expectedSha1Sum
  }

  def doLogin = Action { implicit request =>
    Logger.info("Authenticating user")
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors, routes.AuthenticationController.doLogin())),
      user => Redirect(session.get("returnUrl").getOrElse("/")).withSession(session + ("user" -> user._1) - "returnUrl")
    )
  }

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm, routes.AuthenticationController.doLogin()))
  }

  def logout = Action {
    Redirect(routes.AuthenticationController.login).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
}
