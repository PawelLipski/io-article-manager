package models.copyright

import scala.slick.driver.MySQLDriver.simple._

case class Copyright(
                      ojsId: Int,
                      title: String,
                      correspondingAuthor: CorrespondingAuthor,
                      contribution: List[Contribution],
                      financialDisclosure: String
                      ) {
}

class Copyrights(tag: Tag) extends Table[(Int, Int, String)](tag, Copyrights.TABLE_NAME) {

  def id = column[Int]("id", O.PrimaryKey)

  def ojsArticleId = column[Int]("ojsArticleId")

  // TODO: foreign key to (Corresponding)Authors + add to the projection

  def financialDisclosure = column[String]("financialDisclosure")

  def * = (id, ojsArticleId, financialDisclosure) //<> (Copyright.tupled, Copyright.unapply)
}

object Copyrights {
  val TABLE_NAME = "COPYRIGHTS"
  val copyrights = TableQuery[Copyrights]
}
