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
import org.h2.engine.Database

/**
 * Created by slonka on 20.05.14.
 */
object CopyrightTransferOjsDao {

}
