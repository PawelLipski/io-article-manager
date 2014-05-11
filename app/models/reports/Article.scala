package models.reports

import java.util
import java.util.Date
import models.Author
import models.reports.ArticleStatus.ArticleStatus

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/18/2014
 * Time: 12:25 PM
 */
object ArticleStatus extends Enumeration {
  type ArticleStatus = Value
  val Rejected, Accepted = Value
  def fromString(str:String):ArticleStatus = {
    for (v <- this.values){
      if (v.toString == str)
        return v
    }
    null
  }
}
case class Article (id: Int, title: String, authors: util.Collection[Author], date: Date,
                    status: ArticleStatus, journal: Journal, rewriters: util.Collection[Author]) {

}
