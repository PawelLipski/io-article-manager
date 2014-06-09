package models.copyright

import java.sql.Date
import scala.slick.driver.MySQLDriver.simple._


case class CopyrightTransferRequest(
                                     id: Option[Int],
                                     copyrightId: Option[Int],
                                     ipAddress: String,
                                     dateConfirmed: Date,
                                     dateVerified: Date,
                                     tokenShaSum: String,
                                     status: Int
                                     ) {
}

/*
object CopyrightTransferRequest {
  /def fromTuple(tuple: (Option[Int], Option[Int], String, Date, Date, String, Int)): CopyrightTransferRequest = tuple match {
    case (id: Option[Int], copyrightId: Option[Int], ipAddress: String,
      dateConfirmed: Date, dateVerified: Date, tokenShaSum: String, status: Int) =>
        CopyrightTransferRequest(id, copyrightId, ipAddress, dateConfirmed, dateVerified, tokenShaSum, status)
  }
}*/

class CopyrightTransferRequests(tag: Tag)
  extends Table[CopyrightTransferRequest](tag, CopyrightTransferRequests.TABLE_NAME) {

  def id = column[Option[Int]]("id", O.PrimaryKey)

  def copyrightId = column[Option[Int]]("copyrightId")

  def ipAddress = column[String]("ipAddress")
  
  def dateConfirmed = column[Date]("dateConfirmed")

  def dateVerified = column[Date]("dateVerified")

  def tokenShaSum = column[String]("tokenShaSum")
  
  def status = column[Int]("status")


  def * = (id, copyrightId, ipAddress, dateConfirmed, dateVerified, tokenShaSum, status) <>
    ((CopyrightTransferRequest.apply _).tupled, CopyrightTransferRequest.unapply)


  def copyright = foreignKey("copyrightId", copyrightId, Copyrights.copyrights)(_.id)
}

object CopyrightTransferRequests {
  val TABLE_NAME = "COPYRIGHT_TRANSFER_REQUESTS"
  val copyrightTransferRequests = TableQuery[CopyrightTransferRequests]
}

