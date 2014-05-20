package models

import models.reports.{Article, ArticleAuthor, Journal}
import models.reports.ArticleStatus.ArticleStatus
import models.reports.ArticleStatus
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver
import play.api.db.DB
import play.api.Play.current
import slick.ojs.Tables
import slick.ojs

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/22/2014
 * Time: 2:13 PM
 */
object RankingDataExtractorOjsDao {
  def getPercentOfForeignAuthors(ojsJournalId: Int, year: Int): Double = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val authors = for {
          author <- Tables.Authors //it is really
          article <- ojs.Tables.Articles if author.submissionId === article.articleId && article.journalId === ojsJournalId.asInstanceOf[Long]
        } yield author.country
        val authorsAll = authors.length.run
        val foreignAuthors = authors.filterNot(_ === "poland").length.run
        if ( authorsAll > 0)
          return foreignAuthors / authorsAll * 100
        else
          return 100
    }
  }

  def getNumberOfPublishedArticles(ojsJournalId: Int, year: Int): Int = {
    12
  }

  def getPercentOfForeignReviewers(ojsJournalId: Int, year: Int) = {
    8.0
  }

  def getListOfUnknownAuthors(ojsJournalId: Int, year: Int): Iterable[Author] = {
    List(
      new Author("Name_1", "Surname_1", "Computer Science", null, "2014-01-01", "fake@example.com", "AGH"),
      new Author("Name_2", "Surname_2", "Computer Science", null, "2014-02-01", "fake2@example.com", "MIT"))
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
        ojs.Tables.Journals.list.map(a=> models.reports.Journal(a.journalId.asInstanceOf[Int], a.path))
    }
  }

}
