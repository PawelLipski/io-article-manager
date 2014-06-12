
package dao

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
    withInternalDatabaseTransaction {
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

  def fetchAllTransferRequests(transferIds: Seq[Int]): Seq[CopyrightTransferRequestWrapper] = {
    withInternalDatabaseTransaction {
      implicit session =>

        val transferRequestsHere: Seq[CopyrightTransferRequest] =
          copyrightTransferRequests.filter(_.id inSetBind transferIds).run

        val copyrightIdsHere: Seq[Int] = transferRequestsHere.map(_.copyrightId).flatten

        val copyrightsHere: Seq[Copyright] =
          copyrights.filter(_.id inSetBind copyrightIdsHere).run

        val correspondingAuthorsHere: Seq[CorrespondingAuthor] =
          correspondingAuthors.filter(_.copyrightId inSetBind copyrightIdsHere).run

        val allContributionsHere: Seq[Contribution] =
          contributions.filter(_.copyrightId inSetBind copyrightIdsHere).run

        transferRequestsHere map { transferRequest =>
          val copyright: Copyright =
            copyrightsHere.filter(_.requestId.get == transferRequest.id)(0)
          val correspondingAuthor: CorrespondingAuthor =
            correspondingAuthorsHere.filter(_.copyrightId.get == copyright.id)(0)
          val contributionList: List[Contribution] =
            allContributionsHere.filter(_.copyrightId.get == copyright.id).toList
          CopyrightTransferRequestWrapper(transferRequest, copyright, correspondingAuthor, contributionList)
        }

    }
  }

  def submitTransferRequestAndReturnId(
                                        contributionList: List[Contribution], correspondingAuthor: CorrespondingAuthor, copyright: Copyright, ipAddress: String): Int = {

    withInternalDatabaseTransaction {
      implicit session =>

        val copyrightId: Int = (copyrights returning (copyrights.map(_.id))) += copyright

        contributionList foreach {
          cb =>
            val cbId: Int = contributions returning (contributions.map(_.id)) += cb
            contributions.filter(_.id === cbId).map(_.copyrightId).update(Some(copyrightId))
        }

        val correspondingAuthorId: Int = (correspondingAuthors returning
          (correspondingAuthors.map(_.id))) += correspondingAuthor
        correspondingAuthors.filter(_.id === correspondingAuthorId).map(_.copyrightId).update(Some(copyrightId))

        copyrights.filter(_.id === copyrightId).map(_.correspondingAuthorId).update(Some(correspondingAuthorId))

        val dateConfirmed = new Date(0)
        val dateVerified = new Date(0)
        val tokenShaSum = "(not confirmed yet)"
        val status = SUBMITTED
        val request = CopyrightTransferRequest(
          0, Some(copyrightId), ipAddress, dateConfirmed, dateVerified, tokenShaSum, status)

        val requestId: Int = (copyrightTransferRequests returning
          (copyrightTransferRequests.map(_.id))) += request

        copyrights.filter(_.id === copyrightId).map(_.requestId).update(Some(requestId))

        requestId
    }
  }

  def confirmTransferRequestAndReturnToken(transferId: Int): String = {

    val token = TokenGenerator.generate
    val tokenShaSum = TokenGenerator.toSha(token)

    withInternalDatabaseTransaction {
      implicit session =>
        copyrightTransferRequests
          .filter(_.id === transferId)
          .map(ctr => (ctr.dateConfirmed, ctr.status, ctr.tokenShaSum))
          .update(SqlUtils.getCurrentSqlDate(), CONFIRMED, tokenShaSum)
    }

    token
  }

  def verifyTransferRequest(tokenShaSum: String): Int = {
    withInternalDatabaseTransaction {
      implicit session =>
        copyrightTransferRequests
          .filter(_.tokenShaSum === tokenShaSum)
          .map(ctr => (ctr.dateVerified, ctr.status))
          .update(SqlUtils.getCurrentSqlDate(), VERIFIED)
    }
  }

  /* TODO: requires testing */
  def removeTransferRequest(transferId: Int) = {
    withInternalDatabaseTransaction {
      implicit session =>
        copyrightTransferRequests
          .filter(_.id === transferId)
          .mutate(_.delete())

        val copyrightRow = copyrights
          .filter(_.requestId === transferId)
        val copyrightId: Int = copyrightRow.first.id
        copyrightRow.mutate(_.delete())

        correspondingAuthors
          .filter(_.copyrightId === copyrightId)
          .mutate(_.delete())

        contributions
          .filter(_.copyrightId === copyrightId)
          .mutate(_.delete())
    }
  }

  def verifiedTransferRequestExists(ojsArticleId: Int): Boolean = {
    withInternalDatabaseTransaction {
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

  /*def listTransferRequests(requestIds: Seq[Int]): Seq[CopyrightTransferRequestWrapper] = {
    requestIds map fetchTransferRequest
  }*/

  def listTransferRequestsByJournalAndVolume(ojsJournalId: Long, year: Int, volumeId: Int): Seq[CopyrightTransferRequestWrapper] = {

    val articleIds: Seq[Long] =
      withOjsDatabaseTransaction {
        implicit session =>
          (for {
            article <- ojs.Tables.Articles if
          article.journalId === ojsJournalId &&
            yearFn(Seq(article.lastModified)) === year
          } yield article.articleId).run
      }

    fetchAllTransferRequests(articleIds.map(_.toInt))
  }
}
