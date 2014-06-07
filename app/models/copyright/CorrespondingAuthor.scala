package models.copyright

import scala.slick.driver.MySQLDriver.simple._

case class CorrespondingAuthor(
                               firstName: String,
                               middleName: Option[String],
                               lastName: String,
                               affiliation: String,
                               email: String) {

  override def toString = "Name: "+lastName+
      "\n\rAffiliation: "+affiliation+
      "\n\rE-mail address: "+email

  def getFullName = firstName + " " + middleName.getOrElse("") + " " + lastName
}

class CorrespondingAuthors(tag: Tag)
  extends Table[CorrespondingAuthor](tag, CorrespondingAuthors.TABLE_NAME) {

  // TODO: add to the case class
  //def id = column[Int]("id", O.PrimaryKey)

  def firstName = column[String]("firstName")

  def middleName = column[Option[String]]("middleName")

  def lastName = column[String]("lastName")

  def affiliation = column[String]("affiliation")

  def email = column[String]("email")

  def * = (firstName, middleName, lastName, affiliation, email) <>
    (CorrespondingAuthor.tupled, CorrespondingAuthor.unapply)
}

object CorrespondingAuthors {
  val TABLE_NAME = "CORRESPONDING_AUTHORS"
  val correspondingAuthors = TableQuery[CorrespondingAuthors]
}
