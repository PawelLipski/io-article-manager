package models.dao

import play.api._
import play.api.mvc._
import java.sql.ResultSet
import models.copyright.Contribution
import scala.slick.driver.MySQLDriver.simple._
import play.api.db.DB
import utils.TokenGenerator
import play.api.Play.current
import java.sql.Date
import slick.ojs.Tables
import slick.ojs
import scala.slick.lifted

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
  def getYearsOjsActiveFrom: Option[Int] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session => {
        slick.ojs.Tables.Articles.map(x => yearFn(Seq(x.dateSubmitted))).min.run
      }
    }
  }
  def getYearsOjsActiveTo: Option[Int] = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session => {
        slick.ojs.Tables.Articles.map(x => yearFn(Seq(x.dateSubmitted))).max.run
      }
    }
  }
}