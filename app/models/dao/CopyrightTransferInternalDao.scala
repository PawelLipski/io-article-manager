/**
 * Created by Kuba on 2014-05-08.
 */
package models.dao

import play.api._
import play.api.mvc._
import java.sql.{DriverManager, ResultSet}
import models.copyright.{CopyrightTransferRequest, Copyright}
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver
import play.api.db.DB
import utils.{SqlUtils, TokenGenerator}
import play.api.Play.current
import com.google.common.base.Optional
import java.sql.Date
import scala.slick.lifted
import slick.ojs.Tables
import slick.ojs


object CopyrightTransferInternalDao {

  def saveTransferAndReturnTheToken(filledForm: CopyrightTransferRequest) = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {

      implicit session =>

        val token = TokenGenerator.generate
        val tokenSha = TokenGenerator.toSha(token)

        slick.internal.Tables.Copyrighttransfer.insert(slick.internal.Tables.CopyrighttransferRow(
          0, filledForm.copyrightData.ojsId, filledForm.copyrightData.title,
          filledForm.copyrightData.correspondingAuthor.firstName
            + " " + filledForm.copyrightData.correspondingAuthor.middleName.getOrElse("")
            + " " + filledForm.copyrightData.correspondingAuthor.lastName,
          filledForm.copyrightData.correspondingAuthor.affiliation,
          filledForm.copyrightData.correspondingAuthor.email,
          new Date(filledForm.dateFilled.toDate.getTime),
          filledForm.ipAddress,
          tokenSha,
          false,
          Option[Date](new Date(0)),
          filledForm.copyrightData.financialDisclosure
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

  def removeTransfer(id: Int) = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        slick.internal.Tables.Copyrighttransfer
          .filter(_.id === id)
          .mutate(_.delete())
    }
  }

  def confirmedTransferExists(ojsArticleId: Int): Boolean = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        slick.internal.Tables.Copyrighttransfer
          .filter(ct => 
            ct.ojsarticleid === ojsArticleId && ct.linkconfirmed === true)
          .exists.run
    }
  }

  val yearFn = SimpleFunction[Int]("year")

  def listTransfer(ojsJournalId:Long, year:Int, volumeId: Int):Seq[slick.internal.Tables.CopyrighttransferRow] = {
    var articleIds:Seq[Long] = Seq(0, 1)
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        articleIds = (for {
          article <- ojs.Tables.Articles if article.journalId === ojsJournalId && yearFn(Seq(article.lastModified)) === year
        } yield article.articleId).run
    }

    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        return (for {
          transfer <- slick.internal.Tables.Copyrighttransfer if transfer.ojsarticleid inSetBind articleIds.map(_.toInt)
        } yield transfer).list
    }

  }
}
