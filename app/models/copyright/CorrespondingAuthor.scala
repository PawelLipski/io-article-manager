package models.copyright

/**
 * Created by Zeuko on 07.04.14.
 */
case class CorrespondingAuthor(firstName: String,
                               middleName: Option[String],
                               lastName: String,
                               affiliation: String,
                               email: String) {
  override def toString = "Name: "+lastName+
      "\n\rAffiliation: "+affiliation+
      "\n\rE-mail address: "+email

  def getFullName : String = {
    firstName + " " + middleName.getOrElse("") + " " + lastName
  }
}
