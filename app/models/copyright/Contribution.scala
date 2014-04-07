package models.copyright

/**
 * Created by Zeuko on 07.04.14.
 */
case class Contribution(authorName : String, affiliation : String, contribution: String, percent: Int) {

  override  def toString = "Name: "+authorName+"\n\rAffiliation: "+
      affiliation+"\n\rContribution to paper: "+
      contribution+"\n\rContribution percentage: "+
      percent+"%"

}
