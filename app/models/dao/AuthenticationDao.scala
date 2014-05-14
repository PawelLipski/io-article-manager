package models.dao

import play.api.db.DB
import scala.slick.driver.MySQLDriver.simple._
import play.api.Play.current

object AuthenticationDao {

  // Definition of the SUPPLIERS table
  class User(tag: Tag) extends Table[(Int, String, String)](tag, "INTERNAL_USERS") {
    def id = column[Int]("ID", O.PrimaryKey)

    def name = column[String]("NAME")

    def passwordSha1Sum = column[String]("PASSWORD_SHA1_SUM")

    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, name, passwordSha1Sum)
  }

  val users = TableQuery[User]

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

  def getPasswordSha1SumForUser(userName: String) = {
    "4a0c5a8df03ea9c60cea9a4b876f97f716e42dbf"
  }

}
