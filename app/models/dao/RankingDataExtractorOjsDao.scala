package models

import models.reports.{Article, ArticleAuthor, Journal}
import models.reports.ArticleStatus.ArticleStatus
import models.reports.ArticleStatus
import java.sql.{ResultSet, DriverManager}

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/22/2014
 * Time: 2:13 PM
 */
object RankingDataExtractorOjsDao {

  def getPercentOfForeignAuthors(ojsJournalId: Int, year: Int): Double = {

    val passwd = System.getenv("OJS_DB_PASSWD")
    val connStr = "jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?user=slonka_ojs&password=" + passwd
    var result = 0.0

    val loadDriver = classOf[com.mysql.jdbc.Driver]

    val conn = DriverManager.getConnection(connStr)
    try {
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val napis = "SELECT count(distinct user_id) FROM articles WHERE articles.journal_id = " + ojsJournalId.toString() +
        "AND year(articles.date_submitted) =" + year.toString()

      val rs = statement.executeQuery(napis)

       val contents = rs.getInt("count(distinct user_id)")
      val nexQ = " SELECT count( DISTINCT user_id ) FROM articles INNER JOIN authors ON articles.user_id = authors.author_id WHERE articles.journal_id =" + ojsJournalId.toString() "AND year( articles.date_submitted ) =" + year.toString() +
    "AND authors.country != 'poland'"

      val nt = statement.executeQuery(nexQ)

      val second_var = nt.getInt("count( DISTINCT user_id )")

      result = (second_var/contents)*100
    } finally {
      conn.close()
    }
    result

  }

  def getNumberOfPublishedArticles(ojsJournalId: Int, year: Int): Int = {

    val passwd = System.getenv("OJS_DB_PASSWD")
    val connStr = "jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?user=slonka_ojs&password=" + passwd
    var result = 0

    val loadDriver = classOf[com.mysql.jdbc.Driver]

    val conn = DriverManager.getConnection(connStr)
    try {
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val napis = "SELECT count(distinct article_id) FROM articles WHERE articles.journal_id = " + ojsJournalId.toString() +
        "AND year(articles.date_submitted) =" + year.toString()

      val rs = statement.executeQuery(napis)

      val contents = rs.getInt("count(distinct user_id)")
      result = contents
    } finally {
      conn.close()
    }
    result

  }

  def getPercentOfForeignReviewers(ojsJournalId: Int, year: Int):Double = {

    val passwd = System.getenv("OJS_DB_PASSWD")
    val connStr = "jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?user=slonka_ojs&password=" + passwd
    var result = 0.0

    val loadDriver = classOf[com.mysql.jdbc.Driver]

    val conn = DriverManager.getConnection(connStr)
    try {
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val napis = "SELECT count(distinct reviewer_id) FROM review_assignments INNER JOIN sections ON sections.review_form_id = reviewer_assignments.review_form_id WHERE sections.journal_id = " + ojsJournalId.toString() +
        "AND year( review_assignments.date_assigned) =" + year.toString()

      val rs = statement.executeQuery(napis)

      val contents = rs.getInt("count(distinct user_id)")
      val nexQ = "SELECT count(distinct reviewer_id) FROM review_assignments INNER JOIN sections ON sections.review_form_id = review_assignments.review_form_id INNER JOIN authors ON review_assignments.reviewer_id = authors.author_id WHERE sections.journal_id = " + ojsJournalId.toString() +
        "AND year( review_assignments.date_assigned) =" + year.toString() +
        "AND authors.country != 'poland'"

      val nt = statement.executeQuery(nexQ)

      val second_var = nt.getInt("count(distinct reviewer_id)")

      result = (second_var/contents)*100
    } finally {
      conn.close()
    }
    result

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
    List(
      Journal(1, "New Earth"),
      Journal(2, "Computer Science")
    )
  }

}
