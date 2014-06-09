package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import dao.AuthenticationDao
import org.apache.commons.codec.binary.Hex

object Authentication extends Controller with Secured {

  lazy val loginForm = Form(
    tuple(
      "user" -> text,
      "password" -> text
    ) verifying("Invalid user or password", result => result match {
      case (user, password) if verifyCredentials(user, password) => true
      case _ => false
    })
  )

  private def verifyCredentials(user: String, password: String): Boolean = {

    Logger.info("Trying to authenticate user " + user)

    val sha1 = java.security.MessageDigest.getInstance("SHA-1")
    val actualSha1Sum = new String(Hex.encodeHex(sha1.digest(password.getBytes)))
    val expectedSha1Sum = AuthenticationDao.getPasswordSha1SumForUser(user)

    expectedSha1Sum match {
      case Some(value) if actualSha1Sum == value => {
        Logger.info("User " + user + " successfully authenticated")
        true
      }
      case _ => {
        Logger.info("Failed to authenticate user " + user)
        false
      }
    }
  }

  def doLogin = Action {
    implicit request =>
      loginForm.bindFromRequest.fold(
        formWithErrors => BadRequest(views.html.login(formWithErrors, routes.Authentication.doLogin())),
        user => Redirect(session.get("returnUrl").getOrElse("/")).withSession(session + ("user" -> user._1) - "returnUrl")
      )
  }

  def login = Action {
    implicit request =>
      Ok(views.html.login(loginForm, routes.Authentication.doLogin()))
  }

  def logout = Action {
    Redirect(routes.Authentication.login).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
}
