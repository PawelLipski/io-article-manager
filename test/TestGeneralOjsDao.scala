/**
 * Created by Kuba on 2014-06-03.
 */

import dao._

import org.junit.Ignore

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._


@RunWith(classOf[JUnitRunner])
class TestGeneralOjsDao extends Specification {
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
