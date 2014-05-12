package models.copyright

import org.joda.time.DateTime

case class CopyrightTransferRequest(
                                     id: Int,
                                     copyrightData: Copyright,
                                     dateFilled: DateTime,
                                     ipAddress: String,
                                     status: CopyrightTransferStatus.Status
                                     )
{

}
