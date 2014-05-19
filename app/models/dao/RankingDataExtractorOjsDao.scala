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
          author <- slick.Tables.Authors //it is really
          article <- slick.Tables.Articles if author.submissionId === article.articleId &&
          article.journalId === ojsJournalId.asInstanceOf[Long] && yearFn(Seq(article.dateSubmitted)) === year
        } yield author.country

        val authorsAll = authors.length.run
        val foreignAuthors = authors.filterNot(_ === "poland").length.run
        if (authorsAll > 0)
          foreignAuthors / authorsAll * 100
        else
          100
    }
  }


  def getNumberOfPublishedArticles(ojsJournalId: Int, year: Int): Int = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val articles = for {
          article <- slick.Tables.Articles if article.journalId === ojsJournalId.asInstanceOf[Long] &&
          yearFn(Seq(article.dateSubmitted)) === year
        } yield article.articleId

        articles.length.run
    }
  }

  def getPercentOfForeignReviewers(ojsJournalId: Int, year: Int): Double = {
    10.0
  }

  def getListOfUnknownAuthors(ojsJournalId: Int, year: Int): Iterable[Author] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val authors = for {
          author <- slick.Tables.Authors
          article <- slick.Tables.Articles if author.submissionId === article.articleId &&
          article.journalId === ojsJournalId.asInstanceOf[Long] &&
          yearFn(Seq(article.dateSubmitted)) === year &&
          author.country.isNull
        } yield (author.firstName, author.lastName, author.email, article.dateSubmitted)

        authors.list.map(a => Author(a._1, a._2, null, null, simpleDateFormat.format(a._4.get), a._3, null))
    }
  }

  def getListOfUnknownReviewers(ojsJournalId: Int, year: Int): Iterable[Author] = {
    List(
      new Author("Reviewer 1", "Surname_1", "Computer Science", null, "2014-01-01", "fake@example.com", "UJ"),
      new Author("Reviewer 2", "Surname_2", "Computer Science", null, "2014-02-01", "fake2@example.com", "Univerity of Cambodia"))
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
          author <- slick.Tables.Authors
          article <- slick.Tables.Articles if author.submissionId === article.articleId &&
          ((ojsJournalId == null).asColumnOf[Boolean] || article.journalId === ojsJournalId.asInstanceOf[Long]) &&
          ((year == null).asColumnOf[Boolean] || yearFn(Seq(article.dateSubmitted)) === year) &&
          ((status == null).asColumnOf[Boolean] || article.status === status.id.asInstanceOf[Byte])
          journal <- slick.Tables.Journals if article.journalId === journal.journalId
        } yield (author.firstName, author.lastName, author.email,
            journal.journalId, journal.path,
            article.articleId, article.dateSubmitted, article.status)

        authors.list.map(a => new ArticleAuthor(a._1, a._2, models.reports.Journal(a._4.asInstanceOf[Int], a._5),
          models.reports.Article(a._6.asInstanceOf[Int], null, null, null, ArticleStatus(a._8), null, null), null, a._3))
    }
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
        slick.Tables.Journals.list.map(a => models.reports.Journal(a.journalId.asInstanceOf[Int], a.path))
    }
  }

}
