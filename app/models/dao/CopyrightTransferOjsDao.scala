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

  def getAuthorsForArticle(ojsArticleId: Int): Copyright = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        val titles = slick.ojs.Tables.ArticleSettings.filter(_.articleId === ojsArticleId.asInstanceOf[Long]).filter(_.settingName === "title")
        val authors = slick.ojs.Tables.Authors.filter(_.submissionId === ojsArticleId.asInstanceOf[Long]) leftJoin  slick.ojs.Tables.AuthorSettings on (_.authorId === _.authorId)

        val primary = authors.filter(_._1.primaryContact =!= 0.asInstanceOf[Byte]).list.map(f => CorrespondingAuthor(f._1.lastName, f._2.settingValue.getOrElse(""), f._1.email))

        Copyright(
          ojsArticleId,
          titles.first.settingValue.getOrElse(""),
          primary.head,
          authors.list.map(f => Contribution(f._1.lastName, f._2.settingValue.getOrElse(""), "", 0)),
          ""
        )
    }
  }

  def articleExists(id: Int): Boolean = {
    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        slick.ojs.Tables.ArticleSettings
          .filter(_.articleId === id.asInstanceOf[Long]).length.run > 0
    }
  }

}
