package models.copyright

import scala.slick.driver.MySQLDriver.simple._

case class Contribution(id: Int,
                        transferId: Option[Int],
                        firstName: String,
                        middleName: Option[String],
                        lastName: String,
                        affiliation: String,
                        contribution: String,
                        percent: Int) {

  override def toString = "Name: " + lastName + "\n\rAffiliation: " +
    affiliation + "\n\rContribution to paper: " +
    contribution + "\n\rContribution percentage: " +
    percent + "%"

  def getFullAuthorName = firstName + " " + middleName + " " + lastName
}

object Contribution {

  def assemble(firstName: String, middleName: Option[String], lastName: String, affiliation: String, contribution: String, percent: Int): Contribution = {
    apply(0, None, firstName, middleName, lastName, affiliation, contribution, percent)
  }

  def unassemble(a: Contribution): Option[(String, Option[String], String, String, String, Int)] = {
    Some(a.firstName, a.middleName, a.lastName, a.affiliation, a.contribution, a.percent)
  }
}

class Contributions(tag: Tag)
  extends Table[Contribution](tag, Contributions.TABLE_NAME) {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def copyrightId = column[Option[Int]]("copyrightId")

  def firstName = column[String]("firstName")

  def middleName = column[Option[String]]("middleName")

  def lastName = column[String]("lastName")

  def affiliation = column[String]("affiliation")

  def contribution = column[String]("contribution", O.DBType("TEXT"))

  def percent = column[Int]("percent")


  def * = (id, copyrightId, firstName, middleName, lastName, affiliation, contribution, percent) <>
    ((Contribution.apply _).tupled, Contribution.unapply)


  def copyright = foreignKey("copyrightId", copyrightId, Copyrights.copyrights)(_.id)
}

object Contributions {
  val TABLE_NAME = "CONTRIBUTIONS"
  val contributions = TableQuery[Contributions]
}
