package utils

import play.api.db.DB
import scala.slick.driver.MySQLDriver.simple._
import play.api.Play.current

object DatabaseSessionWrapper {

  def withSession[T](dbName: String)(block: Session => T): T = {
    Database.forDataSource(DB.getDataSource(dbName)).withSession(block)
  }

  def withInternalDatabaseSession[T](block: Session => T): T = withSession("internal") {
    block
  }

  def withOjsDatabaseSession[T](block: Session => T): T = withSession("ojs") {
    block
  }
}
