package models.reports

import models.Author
import models.reports.ArticleStatus.ArticleStatus

/**
 * Author: Mateusz Pszczółka <mateusz.pszczolka@gmail.com>
 * Date: 4/29/2014
 * Time: 6:14 PM
 */
case class ArticleAuthor(username: String,
                  lastname: String,
                  journal: Journal,
                  article: Article,
                  affiliation: String,
                  email: String) {


}

