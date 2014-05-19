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

object CopyrightTransferOjsDao {

  def getAuthorsForArticle(ojsArticleId: Int):String = {

//    Database.forDataSource(DB.getDataSource("internal")).withSession {
//      implicit session =>
//    }
  }

  def saveTransfer(filledForm: CopyrightTransferRequest) {
    Database.forDataSource(DB.getDataSource("internal")).withSession {
      implicit session =>
        slick.internal.Tables.CopyrighttransferRow += (null, filledForm.copyrightData.ojsId , filledForm.copyrightData.title,
          filledForm.copyrightData.correspondingAuthor.name,
          filledForm.copyrightData.correspondingAuthor.affiliation,
          filledForm.copyrightData.correspondingAuthor.email,
          filledForm.dateFilled,
          filledForm.ipAddress,
          filledForm.copyrightData.contribution,
          TokenGenerator.generate(),
          0,
          null)
    }
    //filledForm.
  }
}
