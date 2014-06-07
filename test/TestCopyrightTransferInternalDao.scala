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
import models.dao.CopyrightTransferInternalDao

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._


/**
 * Created by slonka on 19.05.14.
 */
@RunWith(classOf[JUnitRunner])
@Ignore
class TestCopyrightTransferInternalDao extends Specification{
  "Application" should {

    "insert row into CopyrightTransfer table" in new WithApplication {
      /*
      CopyrightTransferInternalDao.saveTransfer(CopyrightTransferRequest(
        null,
        Copyright(1,
          "Text title",
          CorrespondingAuthor("Adam", Option("Jan"), "Nowak", "AGH", "test@gmail.com"),
          List(Contribution("Adam", Option("Jan"), "Nowak", "AGH", "Pelna", 100)),
          "I grant you full rights"),
        DateTime.now(),
        "9.9.9.9",
        CopyrightTransferStatus.UNCONFIRMED))*/
    }

    "update row in CopyrightTransfer table" in new WithApplication {
      CopyrightTransferInternalDao.markTransferAsConfirmed("294785218e9a4034b6c773d02f86fc50")
    }

    "delte row in CopyrightTransger table" in new WithApplication {
      CopyrightTransferInternalDao.removeTransfer(21)
    }
    "select listTransfer in both database" in new WithApplication {
      org.junit.Assert.assertEquals(2, CopyrightTransferInternalDao.listTransfers(3, 2014, 0).length)

    }
  }
}
