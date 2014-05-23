
import java.io.File
import play.api.db.DB
import play.api.GlobalSettings
import play.api.mvc.RequestHeader
import play.api.mvc.Results._
import play.api.Play.current
import play.api.Application
import scala.concurrent._
import scala.reflect.runtime.currentMirror
import scala.slick.model.codegen.SourceCodeGenerator
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.{JdbcProfile, MySQLDriver}
import ExecutionContext.Implicits.global


object Global extends GlobalSettings {

  // called when a route is found, but it was not possible to bind the request parameters
  override def onBadRequest(request: RequestHeader, error: String) = future {
    BadRequest(views.html.errors.badRequest("There was an error in the request!")(request))
  }

  // 500 - internal server error
  override def onError(request: RequestHeader, throwable: Throwable) = future {
    InternalServerError(views.html.errors.error(throwable)(request))
  }

  // 404 - page not found error
  override def onHandlerNotFound(request: RequestHeader) = future {
    NotFound(views.html.errors.pageNotFound()(request))
  }

  override def onStart(app: Application) {
    val slickDriver = "scala.slick.driver.MySQLDriver"
    val outputFolder = "gen/app/"
    var pkg = "slick.ojs"

    if (!new File("gen/app").exists) {
      val driver: JdbcProfile = currentMirror.reflectModule(
        currentMirror.staticModule(slickDriver)
      ).instance.asInstanceOf[JdbcProfile]

      Database.forDataSource(DB.getDataSource("ojs")) withSession {
        implicit session =>
          new SourceCodeGenerator(driver.createModel).writeToFile(slickDriver, outputFolder, pkg)
      }
      pkg = "slick.internal"

      Database.forDataSource(DB.getDataSource("internal")) withSession {
        implicit session =>
          new SourceCodeGenerator(driver.createModel).writeToFile(slickDriver, outputFolder, pkg)
      }
    }
  }

}
