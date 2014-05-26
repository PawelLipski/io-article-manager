package models

import scala.slick.driver.MySQLDriver.simple._
import play.api.db.DB
import play.api.Play.current
import java.text.SimpleDateFormat
import models.reports.ArticleAuthor
import models.reports.ArticleStatus.ArticleStatus
import models.reports.ArticleStatus

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/22/2014
 * Time: 2:13 PM
 */
object RankingDataExtractorOjsDao {

  val yearFn = SimpleFunction[Int]("year")
  val simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

  def getPercentOfForeignAuthors(ojsJournalId: Int, year: Int): Double = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val authors = for {
          author <- slick.ojs.Tables.Authors
          article <- slick.ojs.Tables.Articles if author.submissionId === article.articleId &&
          article.journalId === ojsJournalId.asInstanceOf[Long] && yearFn(Seq(article.dateSubmitted)) === year
        } yield author.country

        val authorsAll = authors.length.run
        val foreignAuthors = authors.filterNot(_ === "PL").length.run
        if (authorsAll > 0)
          foreignAuthors.asInstanceOf[Double] / authorsAll * 100
        else
          100
    }
  }


  def getNumberOfPublishedArticles(ojsJournalId: Int, year: Int): Int = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val articles = for {
          article <- slick.ojs.Tables.Articles if article.journalId === ojsJournalId.asInstanceOf[Long] &&
          yearFn(Seq(article.dateSubmitted)) === year
        } yield article.articleId

        articles.length.run
    }
  }

  def getPercentOfForeignReviewers(ojsJournalId: Int, year: Int): Double = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val reviewers = for {
          reviewer <- slick.ojs.Tables.ReviewAssignments
          user <- slick.ojs.Tables.Users if user.userId === reviewer.reviewerId

          article <- slick.ojs.Tables.Articles if article.articleId === reviewer.submissionId &&
          article.journalId === ojsJournalId.asInstanceOf[Long] &&
          yearFn(Seq(article.dateSubmitted)) === year
        } yield user.country

        val reviewersAll = reviewers.length.run
        val foreignReviewers = reviewers.filterNot(_ === "PL").length.run
        if (reviewersAll > 0)
          foreignReviewers.asInstanceOf[Double] / reviewersAll * 100
        else
          100
    }
  }

  def getListOfUnknownAuthors(ojsJournalId: Int, year: Int): Iterable[Author] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val authors = for {
          authorSettings <- slick.ojs.Tables.AuthorSettings if authorSettings.settingName === "affiliation" && authorSettings.settingValue === ""
          author <- slick.ojs.Tables.Authors if author.authorId === authorSettings.authorId

          articleSetting <- slick.ojs.Tables.ArticleSettings if articleSetting.settingName === "title"
          article <- slick.ojs.Tables.Articles if article.articleId === articleSetting.articleId &&
          author.submissionId === article.articleId &&
          article.journalId === ojsJournalId.asInstanceOf[Long] &&
          yearFn(Seq(article.dateSubmitted)) === year

        } yield (author.firstName, author.lastName, author.email, article.dateSubmitted, articleSetting.settingValue)
        authors.list.map(a => Author(a._1, a._2, a._5.getOrElse(""), null, simpleDateFormat.format(a._4.get), a._3, null))
    }
  }

  def getListOfUnknownReviewers(ojsJournalId: Int, year: Int): Iterable[Author] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val reviewers = for {
          articleSetting <- slick.ojs.Tables.ArticleSettings if articleSetting.settingName === "title"
          article <- slick.ojs.Tables.Articles if article.articleId === articleSetting.articleId &&
          article.journalId === ojsJournalId.asInstanceOf[Long] &&
          yearFn(Seq(article.dateSubmitted)) === year

          reviewer <- slick.ojs.Tables.ReviewAssignments if article.articleId === reviewer.submissionId
          user <- slick.ojs.Tables.Users if user.userId === reviewer.reviewerId
          userSettings <- slick.ojs.Tables.UserSettings if userSettings.userId === user.userId &&
          userSettings.settingName === "affiliation" && userSettings.settingValue === ""

        } yield (user.firstName, user.lastName, user.lastName, article.dateSubmitted, articleSetting.settingValue)

        reviewers.list.map(a => Author(a._1, a._2, a._5.getOrElse(""), null, simpleDateFormat.format(a._4.get), a._3, null))

    }
  }

  /**
   * Params can be null (not filter by fields)
   * @param ojsJournalId
   * @param year
   * @param status
   * @return
   */
  def getListOfAllAuthors(ojsJournalId: Int, year: Int, status: ArticleStatus): Iterable[ArticleAuthor] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val authors = for {
          authorSettings <- slick.ojs.Tables.AuthorSettings if authorSettings.settingName === "affiliation"
          author <- slick.ojs.Tables.Authors if author.authorId === authorSettings.authorId

          articleSetting <- slick.ojs.Tables.ArticleSettings if articleSetting.settingName === "title"
          article <- slick.ojs.Tables.Articles if article.articleId === articleSetting.articleId &&
          author.submissionId === article.articleId &&
          (article.journalId === ojsJournalId.asInstanceOf[Long] || ojsJournalId == 0) &&
          (yearFn(Seq(article.dateSubmitted)) === year || year == 0) &&
          (article.status === ArticleStatus.toByte(status) || ArticleStatus.toByte(status) == -1)

          journal <- slick.ojs.Tables.Journals if article.journalId === journal.journalId
        } yield (author.firstName, author.lastName, authorSettings.settingValue, author.email,
            article.articleId, article.dateSubmitted, articleSetting.settingValue, article.status, journal.journalId, journal.path)

        authors.list.map(a => new ArticleAuthor(a._1, a._2, models.reports.Journal(a._9.asInstanceOf[Int], a._10),
          models.reports.Article(a._5.asInstanceOf[Int], a._7.getOrElse(""),
            null, null, ArticleStatus.fromByte(a._8), null, null), a._3.getOrElse(""), a._4))
    }
  }

  def getListOfAllRewriters(ojsJournalId: Int, year: Int, status: ArticleStatus): Iterable[ArticleAuthor] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val authors = for {
          article <- slick.ojs.Tables.Articles if (article.journalId === ojsJournalId.asInstanceOf[Long] || ojsJournalId == 0) &&
          (yearFn(Seq(article.dateSubmitted)) === year || year == 0) &&
          (article.status === ArticleStatus.toByte(status) || ArticleStatus.toByte(status) == -1)
          articleSetting <- slick.ojs.Tables.ArticleSettings if articleSetting.settingName === "title" &&
          article.articleId === articleSetting.articleId

          reviewer <- slick.ojs.Tables.ReviewAssignments if article.articleId === reviewer.submissionId
          user <- slick.ojs.Tables.Users if user.userId === reviewer.reviewerId
          userSettings <- slick.ojs.Tables.UserSettings if userSettings.userId === user.userId &&
          userSettings.settingName === "affiliation"

          journal <- slick.ojs.Tables.Journals if article.journalId === journal.journalId
        } yield (user.firstName, user.lastName, userSettings.settingValue, user.email,
            article.articleId, article.dateSubmitted, articleSetting.settingValue, article.status, journal.journalId, journal.path)

        authors.list.map(a => new ArticleAuthor(a._1, a._2, models.reports.Journal(a._9.asInstanceOf[Int], a._10),
          models.reports.Article(a._5.asInstanceOf[Int], a._7.getOrElse(""),
            null, null, ArticleStatus.fromByte(a._8), null, null), a._3.getOrElse(""), a._4))
    }
  }

  def getListOfJournals = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        slick.ojs.Tables.Journals.list.map(a => models.reports.Journal(a.journalId.asInstanceOf[Int], a.path))
    }
  }

}
