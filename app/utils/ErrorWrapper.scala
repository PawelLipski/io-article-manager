package utils

import play.api.data._
import play.api.mvc._
import play.api.mvc.Results._

object ErrorWrapper {
  def getFormErrorWrapper[T](implicit request: Request[AnyContent]) = (errors: Form[T]) => {
    val msg = "An internal server error occurred. Please try again."
    BadRequest(views.html.errors.badRequest(msg))
  }: Result
}
