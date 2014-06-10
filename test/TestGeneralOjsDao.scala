import models.copyright._
import models.copyright.Copyright
import models.copyright.CopyrightTransferRequest
import models.copyright.CorrespondingAuthor
import org.joda.time.DateTime
import org.junit.Ignore
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.db.DB
import play.api.test.WithApplication
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver
import models.dao.{GeneralOjsDao, CopyrightTransferInternalDao}

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._


/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 6/3/2014
 * Time: 4:19 PM
 */
@RunWith(classOf[JUnitRunner])
class TestGeneralOjsDao extends Specification{
  "Application" should {
    "read journal active" in new WithApplication() {
      org.junit.Assert.assertEquals(List(2014), GeneralOjsDao.getYearsJournalActive(3))
    }
    "read issues for journal" in new WithApplication() {
      GeneralOjsDao.getIssuesForJournal(3)
    }
  }
}

