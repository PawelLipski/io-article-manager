
package models.dao

import models.copyright._
import models.copyright.Contributions._
import models.copyright.Copyrights._
import models.copyright.CopyrightTransferRequests._
import models.copyright.CorrespondingAuthors._
import models.copyright.CopyrightTransferStatus._
import scala.slick.driver.MySQLDriver.simple._
import play.api.db.DB
import utils.{TokenGenerator, SqlUtils}
import play.api.Play.current
import java.sql.Date
import utils.DatabaseSessionWrapper._
import slick.ojs

object CopyrightTransferInternalDao {

  def fetchTransferRequest(transferId: Int): CopyrightTransferRequestWrapper = {
    withInternalDatabaseSession {
      implicit session =>

        val transferRequest: CopyrightTransferRequest =
          copyrightTransferRequests.filter(_.id === transferId).first

        val copyright: Copyright =
          copyrights.filter(_.id === transferRequest.copyrightId).first

        val correspondingAuthor: CorrespondingAuthor =
          correspondingAuthors.filter(_.id === copyright.correspondingAuthorId).first

        val contributionList: List[Contribution] =
          contributions.filter(_.copyrightId === copyright.id).run.toList

        CopyrightTransferRequestWrapper(transferRequest, copyright, correspondingAuthor, contributionList)
    }
  }

  def submitTransferRequestAndReturnId(
                                        contributionList: List[Contribution], correspondingAuthor: CorrespondingAuthor, copyright: Copyright, ipAddress: String): Int = {

    withInternalDatabaseSession {
      implicit session =>

        val copyrightId: Int = ((copyrights returning
          (copyrights.map(_.id))) += copyright).get

        contributionList foreach {
          cb =>
            val cbId: Int = (contributions returning (contributions.map(_.id)) += cb).get
            contributions.filter(_.id === cbId).map(_.copyrightId).update(Some(copyrightId))
        }

        val correspondingAuthorId: Int = ((correspondingAuthors returning
          (correspondingAuthors.map(_.id))) += correspondingAuthor).get
        correspondingAuthors.filter(_.id === correspondingAuthorId).map(_.copyrightId).update(Some(copyrightId))

        copyrights.filter(_.id == copyrightId).map(_.correspondingAuthorId).update(Some(correspondingAuthorId))

        val dateConfirmed = new Date(0)
        val dateVerified = new Date(0)
        val tokenShaSum = "(not confirmed yet)"
        val status = SUBMITTED
        val request = CopyrightTransferRequest(
          None, Some(copyrightId), ipAddress, dateConfirmed, dateVerified, tokenShaSum, status)

        val requestId: Int = ((copyrightTransferRequests returning
          (copyrightTransferRequests.map(_.id))) += request).get

        copyrights.filter(_.id == copyrightId).map(_.requestId).update(Some(requestId))

        requestId
    }
  }

  def confirmTransferRequestAndReturnToken(transferId: Int): String = {

    val token = TokenGenerator.generate
    val tokenShaSum = TokenGenerator.toSha(token)

    withInternalDatabaseSession {
      implicit session =>
        copyrightTransferRequests
          .filter(_.id === transferId)
          .map(ctr => (ctr.dateConfirmed, ctr.status, ctr.tokenShaSum))
          .update(SqlUtils.getCurrentSqlDate(), CONFIRMED, tokenShaSum)
    }

    token
  }

  def verifyTransferRequest(tokenShaSum: String): Int = {
    withInternalDatabaseSession {
      implicit session =>
        copyrightTransferRequests
          .filter(_.tokenShaSum === tokenShaSum)
          .map(ctr => (ctr.dateVerified, ctr.status))
          .update(SqlUtils.getCurrentSqlDate(), VERIFIED)
    }
  }

  def removeTransferRequest(transferId: Int) = {
    withInternalDatabaseSession {
      implicit session =>
        copyrightTransferRequests
          .filter(_.id === transferId)
          .mutate(_.delete())
    }
  }

  def verifiedTransferRequestExists(ojsArticleId: Int): Boolean = {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        copyrightTransferRequests
          .filter(_.status === VERIFIED)
          .flatMap {
          ctr =>
            copyrights.filter(c =>
              c.id === ctr.copyrightId &&
                c.ojsArticleId === ojsArticleId)
        }.exists.run
    }
  }

  val yearFn = SimpleFunction[Int]("year")

  def listTransferRequests(ojsJournalId: Long, year: Int, volumeId: Int): Seq[CopyrightTransferRequest] = {

    val articleIds: Seq[Long] =
      withOjsDatabaseSession {
        implicit session =>
          (for {
            article <- ojs.Tables.Articles if
          article.journalId === ojsJournalId &&
            yearFn(Seq(article.lastModified)) === year
          } yield article.articleId).run
      }

    withInternalDatabaseSession {
      implicit session =>
        copyrightTransferRequests
          .filter {
          ctr =>
            copyrights.filter(c =>
              c.id === ctr.copyrightId &&
                c.ojsArticleId.inSetBind(articleIds.map(_.toInt))
            ).exists.run
        }.run
    }
  }
}
