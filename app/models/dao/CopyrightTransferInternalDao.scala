/**
 * Created by Kuba on 2014-05-08.
 */
package models.dao

import play.api._
import play.api.mvc._
import java.sql.{DriverManager, ResultSet}
import models.copyright.{CopyrightTransferStatus, CorrespondingAuthor, CopyrightTransferRequest, Copyright}
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
import org.joda.time.DateTime


object CopyrightTransferInternalDao {

  def saveTransfer(filledForm: CopyrightTransferRequest) {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        slick.internal.Tables.Copyrighttransfer.insert(slick.internal.Tables.CopyrighttransferRow(
          0, filledForm.copyrightData.ojsId, filledForm.copyrightData.title,
          filledForm.copyrightData.correspondingAuthor.name,
          filledForm.copyrightData.correspondingAuthor.affiliation,
          filledForm.copyrightData.correspondingAuthor.email,
          new Date(filledForm.dateFilled.toDate().getTime()),
          filledForm.ipAddress,
          TokenGenerator.generate(),
          false,
          Option[Date](new Date(0))
        ))
    }
  }

  def markTransferAsConfirmed(tokenSHA: String) : Int = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        val map = slick.internal.Tables.Copyrighttransfer
          .filter(_.linktokenshasum === tokenSHA)
          .map(row => (row.datelinkconfirmed, row.linkconfirmed))
        return map.update((Option[Date](SqlUtils.getCurrnetSqlDate()), true))
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

  def transferExists(ojsArticleId: Int): Boolean = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        slick.internal.Tables.Copyrighttransfer
          .filter(_.ojsarticleid === ojsArticleId).length.run > 0
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

  def listTransfer(ids: List[Int]) : List[CopyrightTransferRequest] = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        (for {
          transfer <- slick.internal.Tables.Copyrighttransfer if transfer.ojsarticleid inSetBind ids
        } yield transfer).list.map(trans => {
          CopyrightTransferRequest(
            Option(trans.id), Copyright(
              trans.ojsarticleid, trans.title, CorrespondingAuthor(
                trans.correspondingname, trans.correspondingaffiliation, trans.correspondingemail
              ),
              List(), // TODO
              "TODO" // TODO
            ),
            new DateTime(trans.dateformfilled.getTime), trans.filleripaddress, if (trans.linkconfirmed) CopyrightTransferStatus.CONFIRMED else CopyrightTransferStatus.UNCONFIRMED
          )
        })
    }
  }

}
