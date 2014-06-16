package models.copyright

import scala.slick.driver.MySQLDriver.simple._

case class Copyright(id: Int,
                     requestId: Option[Int],
                     correspondingAuthorId: Option[Int],
                     ojsArticleId: Int,
                     title: String,
                     financialDisclosure: String
                      ) {
}

object Copyright {

  def assemble(ojsArticleId: Int, title: String, financialDisclosure: String): Copyright = {
    apply(0, None, None, ojsArticleId, title, financialDisclosure)
  }

  def unassemble(a: Copyright): Option[(Int, String, String)] = {
    Some(a.ojsArticleId, a.title, a.financialDisclosure)
  }
}


class Copyrights(tag: Tag) extends Table[Copyright](tag, Copyrights.TABLE_NAME) {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def requestId = column[Option[Int]]("requestId")

  def correspondingAuthorId = column[Option[Int]]("correspondingAuthorId")

  def ojsArticleId = column[Int]("ojsArticleId")

  def title = column[String]("title")

  def financialDisclosure = column[String]("financialDisclosure", O.DBType("TEXT"))


  def * = (id, requestId, correspondingAuthorId, ojsArticleId, title, financialDisclosure) <>
    ((Copyright.apply _).tupled, Copyright.unapply)


  def request = foreignKey("requestId",
    requestId, CopyrightTransferRequests.copyrightTransferRequests)(_.id)

  def correspondingAuthor = foreignKey("correspondingAuthorId",
    correspondingAuthorId, CorrespondingAuthors.correspondingAuthors)(_.id)
}

object Copyrights {
  val TABLE_NAME = "COPYRIGHTS"
  val copyrights = TableQuery[Copyrights]
}
