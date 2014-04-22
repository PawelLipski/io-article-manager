package models.reports

import java.util
import java.util.Date
import models.Author

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/18/2014
 * Time: 12:25 PM
 */
case class Article (id: Int, title: String, authors: util.Collection[Author], date: Date, journal: Journal) {

}
