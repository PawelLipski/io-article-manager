package models.dao

/**
 * Created by Kuba on 2014-06-02.
 */

import play.api._
import play.api.mvc._
import java.sql.{DriverManager, ResultSet}
import models.copyright.{Contribution, CorrespondingAuthor, CopyrightTransferRequest, Copyright}
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver
import play.api.db.DB
import utils.{SqlUtils, TokenGenerator}
import play.api.Play.current
import com.google.common.base.Optional
import java.sql.Date
import scala.slick.lifted
import slick.ojs.Tables
import slick.ojs

object GeneralOjsDao {

    val yearFn = SimpleFunction[Int]("year")

    def getYearsJournalActive(journalId: Int) :List[Int] = {
      Database.forDataSource(DB.getDataSource("ojs")).withSession {
        implicit session => {
          val years = slick.ojs.Tables.Articles.filter(_.journalId === journalId.asInstanceOf[Long]).map(x => yearFn(Seq(x.dateSubmitted)))
          //years.list.distinct
          years.list
        }
      }
    }

    def getIssuesForJournal(journalId: Int) : List[(Int,String )] = {
      Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session => {
        for {
          article <- slick.ojs.Tables.Articles if article.journalId === journalId.asInstanceOf[Long]
          articleSetting <- slick.ojs.Tables.ArticleSettings if articleSetting.settingName === "title" && article.articleId === articleSetting.articleId
        } yield()

        val issueList = slick.ojs.Tables.Issues.filter(_.journalId === journalId.asInstanceOf[Long]).map(f=>(f.issueId, f.showTitle)).list
      issueList
      }
    }
    }
}