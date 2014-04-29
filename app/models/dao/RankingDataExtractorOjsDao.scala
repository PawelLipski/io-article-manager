package models

import models.reports.Journal

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/22/2014
 * Time: 2:13 PM
 */
object RankingDataExtractorOjsDao {
  def getPercentOfForeignAuthors(ojsJournalId: Int, year: Int): Double =  {
    1.0
  }

  def getNumberOfPublishedArticles(ojsJournalId: Int, year: Int):Int = {
    12
  }

  def getPercentOfForeignReviewers(ojsJournalId: Int, year: Int) = {
    8.0
  }

  def getListOfUnknownAuthors(ojsJournalId: Int, year: Int):Iterable[Author] = {
    List(
      new Author("Name_1", "Surname_1", "Computer Science", null, "2014-01-01", "fake@example.com", "AGH"),
      new Author("Name_2", "Surname_2", "Computer Science", null, "2014-02-01", "fake2@example.com", "MIT"))
  }

  def getListOfUnknownReviewers(ojsJournalId: Int, year: Int):Iterable[Author] = {
    List(
      new Author("Reviewer 1", "Surname_1", "Computer Science", null, "2014-01-01", "fake@example.com", "UJ"),
      new Author("Reviewer 2", "Surname_2", "Computer Science", null, "2014-02-01", "fake2@example.com", "Univerity of Cambodia"))
  }

  def getListOJournals() = {
    List(new Journal(0, "Computer Science"))
  }

}
