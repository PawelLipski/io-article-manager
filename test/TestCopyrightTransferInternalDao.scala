import dao.CopyrightTransferInternalDao

import models.copyright.{Copyright, CorrespondingAuthor, CopyrightTransferStatus, Contribution}
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.Ignore
import org.junit.Assert._
import org.junit.runner._

import play.api.test._
import utils.TokenGenerator

@RunWith(classOf[JUnitRunner])
class TestCopyrightTransferInternalDao extends Specification {
  "Application" should {

    "handle a transfer request" in new WithApplication {

      val contributionList = List(
        Contribution.assemble("Pawel", Some("P."), "Lipski", "AGH", "Lorem", 75),
        Contribution.assemble("Jan", Some("K."), "Kowalski", "AGH", "ipsum", 25)
      )
      val correspondingAuthor =
        CorrespondingAuthor.assemble("Pawel", Some("P."), "Lipski", "AGH", "pawel@lipski.com")
      val copyright =
        Copyright.assemble(1, "Hello world", "Hereby I etc. etc.")

      val ipAddress = "9.9.9.9"
      val transferId: Int = CopyrightTransferInternalDao.submitTransferRequestAndReturnId(
        contributionList, correspondingAuthor, copyright, ipAddress)

      val token: String = CopyrightTransferInternalDao.confirmTransferRequestAndReturnToken(transferId)

      val result = CopyrightTransferInternalDao.verifyTransferRequest(TokenGenerator.toSha(token))
      assertTrue(result != 0)

      val transferRequest = CopyrightTransferInternalDao.fetchTransferRequest(transferId)
      assertEquals(transferRequest.copyrightTransferRequest.status, CopyrightTransferStatus.VERIFIED)
    }
  }
}
