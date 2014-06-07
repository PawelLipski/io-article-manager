package utils

import play.api.db.DB
import scala.slick.driver.MySQLDriver.simple._
import play.api.Play.current
import scala.slick.jdbc.meta.MTable
import models.authentication.Users._

object DatabaseSessionWrapper {

  def withSession[T](dbName: String)(block: Session => T): T = {
    Database.forDataSource(DB.getDataSource(dbName)).withSession(block)
  }

  def ensureInternalDatabaseTablesExist(implicit session: Session) {
    if (MTable.getTables("USERS").list.isEmpty)
      users.ddl.create
  }

  def withInternalDatabaseSession[T](block: Session => T): T = withSession("internal") {
    session =>
      ensureInternalDatabaseTablesExist(session)
      block(session)
  }

  def withOjsDatabaseSession[T](block: Session => T): T = withSession("ojs") {
    block
  }
}
