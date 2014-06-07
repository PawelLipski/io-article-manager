package models.copyright

import scala.slick.driver.MySQLDriver.simple._

case class Contribution(firstName : String,
                        middleName: Option[String],
                        lastName: String,
                        affiliation : String,
                        contribution: String,
                        percent: Int) {

  override  def toString = "Name: "+lastName+"\n\rAffiliation: "+
      affiliation+"\n\rContribution to paper: "+
      contribution+"\n\rContribution percentage: "+
      percent+"%"

  def getFullAuthorName = firstName + " " + middleName.getOrElse("") + " " + lastName
}

class Contributions(tag: Tag)
  extends Table[Contribution](tag, Contributions.TABLE_NAME) {

  // TODO: add to the case class
  //def id = column[Int]("id", O.PrimaryKey)

  def firstName = column[String]("firstName")

  def middleName = column[Option[String]]("middleName")

  def lastName = column[String]("lastName")

  def affiliation = column[String]("affiliation")

  def contribution = column[String]("contribution")

  def percent = column[Int]("percent")

  def * = (firstName, middleName, lastName, affiliation, contribution, percent) <>
    (Contribution.tupled, Contribution.unapply)
}

object Contributions {
  val TABLE_NAME = "CONTRIBUTIONS"
  val contributions = TableQuery[Contributions]
}
