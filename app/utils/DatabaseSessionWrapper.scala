package utils

import play.api.db.DB
import scala.slick.driver.MySQLDriver.simple._
import play.api.Play.current

object DatabaseSessionWrapper {

  def withSession[T](dbName: String, f: Session => T): T = {
    Database.forDataSource(DB.getDataSource(dbName)).withSession(f)
  }

  def withInternalDatabaseSession[T](f: Session => T): T = withSession("internal", f)

  def withOjsDatabaseSession[T](f: Session => T): T = withSession("ojs", f)

}
