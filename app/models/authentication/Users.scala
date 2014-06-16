package models.authentication

import scala.slick.driver.MySQLDriver.simple._

case class User(id: Int, name: String, passwordSha1Sum: String)

class Users(tag: Tag) extends Table[User](tag, Users.TABLE_NAME) {

  def id = column[Int]("id", O.PrimaryKey)

  def name = column[String]("name")

  def passwordSha1Sum = column[String]("passwordSha1Sum")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name, passwordSha1Sum) <> (User.tupled, User.unapply)
}

object Users {
  val TABLE_NAME = "USERS"
  val users = TableQuery[Users]
}
