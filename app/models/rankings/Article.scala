package models.rankings

import java.util
import java.util.Date
import models.rankings.ArticleStatus.ArticleStatus

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/18/2014
 * Time: 12:25 PM
 */
object ArticleStatus extends Enumeration {
  type ArticleStatus = Value
  val Rejected, Accepted = Value

  def fromString(str: String): ArticleStatus = {
    for (v <- this.values) {
      if (v.toString == str)
        return v
    }
    null
  }

  def fromByte(id: Byte): ArticleStatus = {
    if (id == 3) Rejected else Accepted
  }

  def toByte(status: ArticleStatus): Byte = {
    if (status == Rejected) 3 else if (status == Accepted) 1 else -1
  }
}

case class Article(id: Int, title: String, authors: util.Collection[Author], date: Date,
                   status: ArticleStatus, journal: Journal, rewriters: util.Collection[Author]) {

}
