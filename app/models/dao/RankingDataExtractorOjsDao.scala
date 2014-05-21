package models

import models.reports.{Article, ArticleAuthor, Journal}
import models.reports.ArticleStatus.ArticleStatus
import models.reports.ArticleStatus
import scala.slick.driver.MySQLDriver.simple._
import play.api.db.DB
import play.api.Play.current
import java.text.SimpleDateFormat

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

          article <- slick.ojs.Tables.Articles if article.userId === user.userId &&
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
          authorSettings <- slick.ojs.Tables.AuthorSettings if authorSettings.settingName === "affiliation" && authorSettings.settingValue === ""
          author <- slick.ojs.Tables.Authors if author.authorId === authorSettings.authorId

          articleSetting <- slick.ojs.Tables.ArticleSettings if articleSetting.settingName === "title"
          article <- slick.ojs.Tables.Articles if article.articleId === articleSetting.articleId &&
          author.submissionId === article.articleId && article.journalId === ojsJournalId.asInstanceOf[Long] &&
          yearFn(Seq(article.dateSubmitted)) === year

          user <- slick.ojs.Tables.Users if article.userId === user.userId
          reviewer <- slick.ojs.Tables.ReviewAssignments if user.userId === reviewer.reviewerId
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
      /*
        Database.forDataSource(DB.getDataSource("ojs")).withSession {
          implicit session =>
            val authors = for {
              author <- slick.ojs.Tables.Authors
              article <- slick.ojs.Tables.Articles if author.submissionId === article.articleId
                        ((ojsJournalId == 0).asColumnOf[Boolean] || article.journalId === ojsJournalId.asInstanceOf[Long]) &&
                          ((year == 0).asColumnOf[Boolean] || yearFn(Seq(article.dateSubmitted)) === year)
                        ((status == null).asColumnOf[Boolean] || article.status === status.id.asInstanceOf[Byte])
              journal <- slick.ojs.Tables.Journals if article.journalId === journal.journalId
            } yield (author.firstName, author.lastName, author.email,
                journal.journalId, journal.path,
                article.articleId, article.dateSubmitted, article.status)

            authors.list.map(a => new ArticleAuthor(a._1, a._2, models.reports.Journal(a._4.asInstanceOf[Int], a._5),
              models.reports.Article(a._6.asInstanceOf[Int], null, null, null, ArticleStatus(a._8), null, null), null, a._3))
        }
        */
    val newEarth = Journal(1, "New Earth")
    val computerScience = Journal(2, "Computer Science")

    List(
      new ArticleAuthor("Jacek", "Nowak", newEarth, Article(0, "Eartquakes", null, null, ArticleStatus.Accepted, computerScience, null), "Poland", "fake@example.com"),
      new ArticleAuthor("Marcin", "Adamczyk", computerScience, Article(0, "Diodes", null, null, ArticleStatus.Accepted, computerScience, null), "Poland", "tea@example.com"),
      new ArticleAuthor("Jacek", "Nowak", computerScience, Article(0, "Something", null, null, ArticleStatus.Accepted, computerScience, null), "England", "fake@example.com"),
      new ArticleAuthor("Andrzej", "Kowalski", computerScience, Article(0, "NP-hard problems", null, null, ArticleStatus.Rejected, computerScience, null), "Poland", "fake@example.com")
    ).filter(p => ojsJournalId == 0 || p.journal.id == ojsJournalId).filter(p => status == null || p.article.status == status)
  }

  def getListOfAllRewriters(ojsJournalId: Int, year: Int, status: ArticleStatus): Iterable[ArticleAuthor] = {
    val newEarth = Journal(1, "New Earth")
    val computerScience = Journal(2, "Computer Science")

    List(
      new ArticleAuthor("JacekR", "Nowak", newEarth, Article(0, "Eartquakes", null, null, ArticleStatus.Rejected, computerScience, null), "Poland", "fake@example.com"),
      new ArticleAuthor("MarcinR", "Adamczyk", computerScience, Article(0, "Diodes", null, null, ArticleStatus.Accepted, computerScience, null), "Poland", "tea@example.com"),
      new ArticleAuthor("JacekR", "Nowak", computerScience, Article(0, "Something", null, null, ArticleStatus.Accepted, computerScience, null), "England", "fake@example.com"),
      new ArticleAuthor("AndrzejR", "Kowalski", computerScience, Article(0, "NP-hard problems", null, null, ArticleStatus.Accepted, computerScience, null), "Poland", "fake@example.com")
    ).filter(p => ojsJournalId == 0 || p.journal.id == ojsJournalId).filter(p => status == null || p.article.status == status)

  }

  def getListOfJournals = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        slick.ojs.Tables.Journals.list.map(a => models.reports.Journal(a.journalId.asInstanceOf[Int], a.path))
    }
  }

}
