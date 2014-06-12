
import java.io.File
import models.authentication.Users
import models.copyright._
import play.api.GlobalSettings
import play.api.mvc.RequestHeader
import play.api.mvc.Results._
import play.api.Application
import scala.concurrent._
import scala.reflect.runtime.currentMirror
import scala.slick.model.codegen.SourceCodeGenerator
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver.SchemaDescription
import scala.slick.driver.JdbcProfile
import scala.slick.jdbc.meta.MTable
import scala.slick.lifted.AbstractTable
import utils.DatabaseSessionWrapper._
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
    ensureInternalDatabaseTablesExist
  }

  def ensureTableExists[T <: AbstractTable[_]](ddl: SchemaDescription, name: String)(implicit session: Session) {
    if (MTable.getTables(name).list.isEmpty)
      ddl.create
  }

  def ensureInternalDatabaseTablesExist {
    withInternalDatabaseTransaction {
      implicit session =>
        ensureTableExists(Contributions.contributions.ddl, Contributions.TABLE_NAME)
        ensureTableExists(Copyrights.copyrights.ddl, Copyrights.TABLE_NAME)
        ensureTableExists(CopyrightTransferRequests.copyrightTransferRequests.ddl, CopyrightTransferRequests.TABLE_NAME)
        ensureTableExists(CorrespondingAuthors.correspondingAuthors.ddl, CorrespondingAuthors.TABLE_NAME)
        ensureTableExists(Users.users.ddl, Users.TABLE_NAME)
    }
  }
}
