package models.copyright

import scala.slick.driver.MySQLDriver.simple._

case class CorrespondingAuthor(id: Int,
                               copyrightId: Option[Int],
                               firstName: String,
                               middleName: Option[String],
                               lastName: String,
                               affiliation: String,
                               email: String) {

  override def toString = "Name: " + lastName +
    "\n\rAffiliation: " + affiliation +
    "\n\rE-mail address: " + email

  def getFullName = firstName + " " + middleName.getOrElse("") + " " + lastName
}

object CorrespondingAuthor {

  def assemble(firstName: String, middleName: Option[String], lastName: String, affiliation: String, email: String): CorrespondingAuthor = {
    apply(0, None, firstName, middleName, lastName, affiliation, email)
  }

  def unassemble(a: CorrespondingAuthor): Option[(String, Option[String], String, String, String)] = {
    Some(a.firstName, a.middleName, a.lastName, a.affiliation, a.email)
  }
}

class CorrespondingAuthors(tag: Tag)
  extends Table[CorrespondingAuthor](tag, CorrespondingAuthors.TABLE_NAME) {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def copyrightId = column[Option[Int]]("copyrightId")

  def firstName = column[String]("firstName")

  def middleName = column[Option[String]]("middleName")

  def lastName = column[String]("lastName")

  def affiliation = column[String]("affiliation")

  def email = column[String]("email")


  def * = (id, copyrightId, firstName, middleName, lastName, affiliation, email) <>
    ((CorrespondingAuthor.apply _).tupled, CorrespondingAuthor.unapply)


  def copyright = foreignKey("copyrightId", copyrightId, Copyrights.copyrights)(_.id)
}

object CorrespondingAuthors {
  val TABLE_NAME = "CORRESPONDING_AUTHORS"
  val correspondingAuthors = TableQuery[CorrespondingAuthors]
}
