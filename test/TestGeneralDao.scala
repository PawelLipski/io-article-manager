/**
 * Created by Kuba on 2014-06-03.
 */

import models.dao._

import org.joda.time.DateTime
import org.junit.Ignore
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.db.DB
import play.api.test.WithApplication
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver
import models.dao.CopyrightTransferInternalDao

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._



@RunWith(classOf[JUnitRunner])
class TestGeneralDao extends Specification{
  "Application" should {

    "Get years from database" in new WithApplication {

      org.junit.Assert.assertEquals(List(2014),GeneralOjsDao.getYearsJournalActive(4))
    }

    "Get issues" in new WithApplication {
      org.junit.Assert.assertEquals(List((2,null),(2,null),(2,0),(2,0),(2,0),(2,"Abba")), GeneralOjsDao.getIssuesForJournal(2))
    }

    "select listTransfer in both database" in new WithApplication {
      org.junit.Assert.assertEquals(List(2014), GeneralOjsDao.getYearsJournalActive(4))

    }
    "read journal active" in new WithApplication() {
      org.junit.Assert.assertEquals(List(2014), GeneralOjsDao.getYearsJournalActive(3))
    }
  }
}
