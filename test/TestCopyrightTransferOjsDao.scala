import models.copyright._
import models.copyright.Copyright
import models.copyright.CopyrightTransferRequest
import models.copyright.CorrespondingAuthor
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.db.DB
import play.api.test.WithApplication
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver
import models.dao.CopyrightTransferOjsDao

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._


/**
 * Created by slonka on 19.05.14.
 */
@RunWith(classOf[JUnitRunner])
class TestCopyrightTransferOjsDao extends Specification{
  "Application" should {
    "insert row into CopyrightTransfer table" in new WithApplication {

      CopyrightTransferOjsDao.saveTransfer(CopyrightTransferRequest( null,
        Copyright(1,
          "Text title",
          CorrespondingAuthor("Adam", "Nowak", "test@gmail.com"),
          List(Contribution("Adam Nowak", "AGH", "Pelna", 100)),
          "I grant you full rights"),
        DateTime.now(),
        "9.9.9.9",
        CopyrightTransferStatus.UNCONFIRMED ))
    }
  }
}
