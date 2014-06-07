package models.copyright

import java.sql.Date
import scala.slick.driver.MySQLDriver.simple._
import org.joda.time.DateTime

case class CopyrightTransferRequest(
                                     id: Option[Int],
                                     copyrightData: Copyright,
                                     dateFilled: DateTime,
                                     ipAddress: String,
                                     status: CopyrightTransferStatus.Status
                                     ) {
}

class CopyrightTransferRequests(tag: Tag)
  extends Table[(Int, Date, String, Int)](tag, CopyrightTransferRequests.TABLE_NAME) {

  def id = column[Int]("id", O.PrimaryKey)

  // TODO: foreign key to CopyrightData + add to the projection

  // TODO: what about the time?
  def dateFilled = column[Date]("dateFilled")

  def ipAddress = column[String]("ipAddress")

  def status = column[Int]("status")

  def * = (id, dateFilled, ipAddress, status) //<> (CopyrightTransferRequests.tupled, CopyrightTransferRequests.unapply)
}

object CopyrightTransferRequests {
  val TABLE_NAME = "COPYRIGHT_TRANSFER_REQUESTS"
  val copyrightTransferRequests = TableQuery[CopyrightTransferRequests]
}
