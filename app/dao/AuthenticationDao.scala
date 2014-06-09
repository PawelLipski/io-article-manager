package dao

import scala.slick.driver.MySQLDriver.simple._
import models.authentication.Users._
import utils.DatabaseSessionWrapper._

object AuthenticationDao {

  def getPasswordSha1SumForUser(userName: String): Option[String] = {
    withInternalDatabaseSession {
      implicit session =>
        val found = users.filter(_.name === userName).map(_.passwordSha1Sum)
        found.firstOption
    }
  }

}
