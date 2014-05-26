package controllers

import play.api.mvc._
import play.api.mvc.SimpleResult

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
    Results.Redirect(routes.Authentication.login()).withSession(("returnUrl", request.path))

  def withAuth(f: => String => Request[AnyContent] => Result): Action[(Action[AnyContent], AnyContent)] = {
    withAuth(BodyParsers.parse.anyContent)(f)
  }

  def withAuth[A](p: BodyParser[A])(f: => String => Request[A] => Result): Action[(Action[A], A)] = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(p)(request => f(user)(request))
    }
  }
}
