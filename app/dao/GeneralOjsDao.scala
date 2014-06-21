package dao

import scala.slick.driver.MySQLDriver.simple._
import utils.DatabaseSessionWrapper._
import scala.slick.lifted

object GeneralOjsDao {

  val yearFn = SimpleFunction[Int]("year")

  def getYearsJournalActive(journalId: Int): List[Int] = {
    withOjsDatabaseTransaction {
      implicit session => {
        slick.ojs.Tables.Articles.filter(_.journalId === journalId.asInstanceOf[Long]).map(x => yearFn(Seq(x.dateSubmitted))).filter(_.isNotNull).groupBy(x => x).map(_._1).list
      }
    }
  }

  def getIssuesForJournal(journalId: Int): List[(Long, String)] = {
    withOjsDatabaseTransaction {
      implicit session => {
        (for {
          issue <- slick.ojs.Tables.Issues if issue.journalId === journalId.asInstanceOf[Long]
          issueSetting <- slick.ojs.Tables.IssueSettings if issueSetting.settingName === "title" && issue.issueId === issueSetting.issueId
        } yield issueSetting).list.filter(_.settingValue.isDefined).map(issueTitleSetting => (issueTitleSetting.issueId, issueTitleSetting.settingValue.get))
      }
    }
  }

  def getListOfJournals = {
    withOjsDatabaseTransaction {
      implicit session =>
        slick.ojs.Tables.Journals.list.map(a => models.rankings.Journal(a.journalId.asInstanceOf[Int], a.path))
    }
  }
  def getYearsOjsActiveFrom: Option[Int] = {
    withOjsDatabaseTransaction {
      implicit session => {
        slick.ojs.Tables.Articles.map(x => yearFn(Seq(x.dateSubmitted))).min.run
      }
    }
  }
  def getYearsOjsActiveTo: Option[Int] = {
    withOjsDatabaseTransaction {
      implicit session => {
        slick.ojs.Tables.Articles.map(x => yearFn(Seq(x.dateSubmitted))).max.run
      }
    }
  }
  def getAllYears: Set[Int] = {
    var journals = GeneralOjsDao.getListOfJournals
    var yearsActive = List[Int]()
    for (jour <- journals) {
      yearsActive = yearsActive ++ (GeneralOjsDao.getYearsJournalActive(jour.id))
    }
    var returned = yearsActive.toSet
    returned
  }
}
