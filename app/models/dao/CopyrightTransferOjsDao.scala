/**
 * Created by Kuba on 2014-05-08.
 */
package models.dao

import play.api._
import play.api.mvc._
import java.sql.{DriverManager, ResultSet}
import models.copyright.{CopyrightTransferRequest, Copyright}
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver
import play.api.db.DB
import utils.TokenGenerator
import play.api.Play.current
import com.google.common.base.Optional
import java.sql.Date


object CopyrightTransferOjsDao {

  def getAuthorsForArticle(ojsArticleId: Int):String = {

//    Database.forDataSource(DB.getDataSource("internal")).withSession {
//      implicit session =>
//    }
    ""
  }

  def saveTransfer(filledForm: CopyrightTransferRequest) {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        slick.internal.Tables.Copyrighttransfer.insert(slick.internal.Tables.CopyrighttransferRow(
          0, filledForm.copyrightData.ojsId, filledForm.copyrightData.title,
          filledForm.copyrightData.correspondingAuthor.name,
          filledForm.copyrightData.correspondingAuthor.affiliation,
          filledForm.copyrightData.correspondingAuthor.email,
          new Date(filledForm.dateFilled.toDate().getTime()),
          filledForm.ipAddress,
          TokenGenerator.generate(),
          false,
          new Date(0)
        ))
    }
    //filledForm.
  }
}
