package models.reports

import models.reports.ArticleStatus.ArticleStatus

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/18/2014
 * Time: 1:44 PM
 */
case class AuthorList (journal: Journal, year : Int, articleStatus: ArticleStatus){

}
case class AuthorListFilter(journal: Option[Journal], year: Option[Int], articleStatus: Option[ArticleStatus]){

}