package models.authentication

import scala.slick.driver.MySQLDriver.simple._

class User(tag: Tag) extends Table[(Int, String, String)](tag, "INTERNAL_USERS") {
  def id = column[Int]("ID", O.PrimaryKey)

  def name = column[String]("NAME")

  def passwordSha1Sum = column[String]("PASSWORD_SHA1_SUM")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name, passwordSha1Sum)
}

object User {
  val users = TableQuery[User]
}
