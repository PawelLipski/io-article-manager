package dao

import scala.slick.driver.MySQLDriver.simple._
import play.api.db.DB
import play.api.Play.current

object GeneralOjsDao {

  val yearFn = SimpleFunction[Int]("year")

  def getYearsJournalActive(journalId: Int): List[Int] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session => {
        slick.ojs.Tables.Articles.filter(_.journalId === journalId.asInstanceOf[Long]).map(x => yearFn(Seq(x.dateSubmitted))).filter(_.isNotNull).groupBy(x => x).map(_._1).list
      }
    }
  }

  def getIssuesForJournal(journalId: Int): List[(Long, String)] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session => {
        (for {
          issue <- slick.ojs.Tables.Issues if issue.journalId === journalId.asInstanceOf[Long]
          issueSetting <- slick.ojs.Tables.IssueSettings if issueSetting.settingName === "title" && issue.issueId === issueSetting.issueId
        } yield issueSetting).list.filter(_.settingValue.isDefined).map(issueTitleSetting => (issueTitleSetting.issueId, issueTitleSetting.settingValue.get))
      }
    }
  }

  def getListOfJournals = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        slick.ojs.Tables.Journals.list.map(a => models.reports.Journal(a.journalId.asInstanceOf[Int], a.path))
    }
  }
}
