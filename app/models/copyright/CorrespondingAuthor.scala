package models.copyright

/**
 * Created by Zeuko on 07.04.14.
 */
case class CorrespondingAuthor(name: String, affiliation: String, email: String) {
  override def toString = "Name: "+name+
      "\n\rAffiliation: "+affiliation+
      "\n\rE-mail address: "+email
}
