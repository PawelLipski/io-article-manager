package models

import org.joda.time.DateTime

/**
 * Created by Piotrek on 2014-04-07.
 */
case class CopyrightTransferForm(
                                  copyrightData: CopyrightData,
                                  date: DateTime,
                                  ipAddress: String
                                  ) {
}
