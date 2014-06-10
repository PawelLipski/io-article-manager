package models.copyright

/**
 * Created by Zeuko on 07.04.14.
 */
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

  def getFullAuthorName : String = {
    firstName + " " + middleName.getOrElse("") + " " + lastName
  }

}
