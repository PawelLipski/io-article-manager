package models.authentication

import scala.slick.driver.MySQLDriver.simple._

class User(tag: Tag) extends Table[(Int, String, String)](tag, "USERS") {
  def id = column[Int]("id", O.PrimaryKey)

  def name = column[String]("name")

  def passwordSha1Sum = column[String]("passwordSha1Sum")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name, passwordSha1Sum)
}

object User {
  val users = TableQuery[User]
}
