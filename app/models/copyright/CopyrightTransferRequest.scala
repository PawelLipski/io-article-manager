package models.copyright

import org.joda.time.DateTime

case class CopyrightTransferRequest(
                                     id: Option[Int],
                                     copyrightData: Copyright,
                                     dateFilled: DateTime,
                                     ipAddress: String,
                                     status: CopyrightTransferStatus.Status,
                                     financialDisclosure: String
                                     )
{

}
