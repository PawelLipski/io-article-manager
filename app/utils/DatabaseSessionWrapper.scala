package utils

import play.api.db.DB
import scala.slick.driver.MySQLDriver.simple._
import play.api.Play.current

object DatabaseSessionWrapper {

  def withTransaction[T](dbName: String)(block: Session => T): T = {
    Database.forDataSource(DB.getDataSource(dbName)).withTransaction {
      implicit session =>
        try {
          block(session)
        } catch {
          case e: Exception =>
            session.rollback()
            throw e
        }
    }
  }

  def withInternalDatabaseTransaction[T](block: Session => T): T = withTransaction("internal") {
    block
  }

  def withOjsDatabaseTransaction[T](block: Session => T): T = withTransaction("ojs") {
    block
  }
}
