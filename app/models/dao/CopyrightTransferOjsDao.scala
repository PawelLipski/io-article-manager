package models.dao

import play.api._
import play.api.mvc._
import java.sql.{DriverManager, ResultSet}
import models.copyright.{CorrespondingAuthor, CopyrightTransferRequest, Copyright}
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

/**
 * Created by slonka on 20.05.14.
 */
object CopyrightTransferOjsDao {

  /* select * from slonka_ojs238.authors as ojs_a join slonka_io.CopyrightTransfer as io_c left join
   slonka_io.AuthorsContribution io_a on ojs_a.submission_id = io_c.ojsArticleId and io_c.id = io_a.id
   where ojs_a.submission_id = 1 */

  /*
  def getAuthorsForArticle(ojsArticleId: Int): CopyrightTransferRequest = {
    var authors :Seq[AuthorsRow] = Seq(0, 1)

    Database.forDataSource(DB.getDataSource("ojs")).withSession {
      implicit session =>
        authors = slick.ojs.Tables.Authors.filter(_.submissionId === ojsArticleId.asLong)

        var title =
          slick.ojs.Tables.ArticleSettings.filter(_.articleId === ojsArticleId.asLong, _.settingName === "title")

        var affiliation =
          slick.ojs.Tables.ArticleSettings.filter(_.articleId === ojsArticleId.asLong, _.settingName === "affiliation")
    }

    var correspondingAuthor = authors.filter(_.primaryContact === 1)


    TODO SOMEONE ELSE WILL NOT COMLETE ON TIME
    CopyrightTransferRequest(ojsArticleId,
      Copyright(ojsArticleId, title,
       CorrespondingAuthor( correspondingAuthor.lift(0).firstName,  )
      )
    )
  }*/
}
