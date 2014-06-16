package utils

import java.sql.Date

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 5/19/2014
 * Time: 6:50 PM
 */
object SqlUtils {
  def getCurrentSqlDate(): java.sql.Date = {
    var cal = java.util.Calendar.getInstance()
    var utilDate = cal.getTime
    var sqlDate = new Date(utilDate.getTime())
    sqlDate
  }
}
