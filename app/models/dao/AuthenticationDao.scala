package models.dao

import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.MySQLDriver.simple._
import models.authentication.User._
import scala.slick.jdbc.meta.MTable

object AuthenticationDao {

  def printUserNames {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        users.ddl.create
        users += (1, "admin", "4a0c5a8df03ea9c60cea9a4b876f97f716e42dbf") // ala123
        users foreach {
          case (id, name, passwordSha1Sum) =>
            println(id + "  " + name + "  " + passwordSha1Sum)
        }
    }
  }

  def getPasswordSha1SumForUser(userName: String): Option[String] = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        if (MTable.getTables("USERS").list.isEmpty)
          users.ddl.create
        val found = users.filter(_.name === userName).map(_.passwordSha1Sum)
        found.firstOption
    }
  }

}
