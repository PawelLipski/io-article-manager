package models.dao

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


object CopyrightTransferOjsDao {

  def getJournalIDForArticle(ojsArticleId: Int): Long = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session => {
        val journalId = slick.ojs.Tables.Articles.filter(_.articleId === ojsArticleId.asInstanceOf[Long]).map(f => f.journalId).first()
        journalId
      }
    }
  }

  def getAuthorsForArticle(ojsArticleId: Int): Copyright = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val title = slick.ojs.Tables.ArticleSettings.filter(_.articleId === ojsArticleId.asInstanceOf[Long]).filter(_.settingName === "title").firstOption()
        val authors = slick.ojs.Tables.Authors.filter(_.submissionId === ojsArticleId.asInstanceOf[Long]) leftJoin  slick.ojs.Tables.AuthorSettings on (_.authorId === _.authorId)

        val primary = authors.filter(_._1.primaryContact =!= 0.asInstanceOf[Byte]).list.map(f => CorrespondingAuthor(f._1.firstName, f._1.middleName, f._1.lastName, f._2.settingValue.getOrElse(""), f._1.email))

        Copyright(
          ojsArticleId,
          if (title.isEmpty) "" else title.get.settingValue.getOrElse(""),
          primary.head,
          authors.list.map(f => Contribution(f._1.firstName, f._1.middleName, f._1.lastName, f._2.settingValue.getOrElse(""), "", 0)),
          ""
        )
    }
  }

  def articleExists(id: Int): Boolean = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        slick.ojs.Tables.ArticleSettings
          .filter(_.articleId === id.asInstanceOf[Long]).exists.run
    }
  }

}
