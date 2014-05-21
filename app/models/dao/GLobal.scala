import play.api.GlobalSettings
import play.api.mvc.RequestHeader
import play.api.mvc.Results._
import scala.concurrent._
import ExecutionContext.Implicits.global

object Global extends GlobalSettings {

  // called when a route is found, but it was not possible to bind the request parameters
  override def onBadRequest(request: RequestHeader, error: String) = future {
    BadRequest(views.html.errors.onHandlerNotFound()(request))
  }

  // 500 - internal server error
  override def onError(request: RequestHeader, throwable: Throwable) = future {
    InternalServerError(views.html.errors.onError(throwable)(request))
  }

  // 404 - page not found error
  override def onHandlerNotFound(request: RequestHeader) = future {
    NotFound(views.html.errors.onHandlerNotFound()(request))
  }

}
