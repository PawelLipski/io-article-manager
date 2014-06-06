
package models.dao

import java.sql.DriverManager
import models.copyright.CopyrightTransferRequest
import scala.slick.driver.MySQLDriver.simple._
import play.api.db.DB
import utils.{SqlUtils, TokenGenerator}
import play.api.Play.current
import java.sql.Date
import slick.internal.Tables._
import slick.ojs


object CopyrightTransferInternalDao {

  def saveTransferAndReturnTheToken(ctr: CopyrightTransferRequest) = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {

      implicit session =>

        val token = TokenGenerator.generate
        val tokenSha = TokenGenerator.toSha(token)

        Copyrighttransfer.insert(CopyrighttransferRow(
          0, ctr.copyrightData.ojsId, ctr.copyrightData.title,
          ctr.copyrightData.correspondingAuthor.firstName
            + " " + ctr.copyrightData.correspondingAuthor.middleName.getOrElse("")
            + " " + ctr.copyrightData.correspondingAuthor.lastName,
          ctr.copyrightData.correspondingAuthor.affiliation,
          ctr.copyrightData.correspondingAuthor.email,
          new Date(ctr.dateFilled.toDate.getTime),
          ctr.ipAddress,
          tokenSha,
          false,
          Option[Date](new Date(0)),
          ctr.copyrightData.financialDisclosure
        ))

        token
    }
  }

  def markTransferAsConfirmed(tokenSha: String): Int = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        val row = slick.internal.Tables.Copyrighttransfer
          .filter(_.linktokenshasum === tokenSha)
          .map(row => (row.datelinkconfirmed, row.linkconfirmed))
        row.update((Option[Date](SqlUtils.getCurrentSqlDate()), true))
    }
  }

  def removeTransfer(transferId: Int) = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        Copyrighttransfer
          .filter(_.id === transferId)
          .mutate(_.delete())
    }
  }

  def confirmedTransferExists(ojsArticleId: Int): Boolean = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        Copyrighttransfer
          .filter(ct => 
            ct.ojsarticleid === ojsArticleId && ct.linkconfirmed === true)
          .exists.run
    }
  }

  val yearFn = SimpleFunction[Int]("year")

  def listTransfers(ojsJournalId: Long, year: Int, volumeId: Int): Seq[CopyrighttransferRow] = {
    var articleIds:Seq[Long] = Seq(0, 1)
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        articleIds = (for {
          article <- ojs.Tables.Articles if
            article.journalId === ojsJournalId &&
            yearFn(Seq(article.lastModified)) === year
        } yield article.articleId).run
    }

    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        return (for {
          transfer <- Copyrighttransfer if 
            transfer.ojsarticleid inSetBind articleIds.map(_.toInt)
        } yield transfer).list
    }

  }
}
