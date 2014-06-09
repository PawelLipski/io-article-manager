package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.rankings.{Report, ArticleStatus, AuthorList, Journal}
import views.html
import models.RankingDataExtractorOjsDao
import utils.ErrorWrapper

object Lists extends Controller with Secured {
  val form: Form[AuthorList] = Form(
    mapping(
      "journal" -> mapping(
        "id" -> optional(number)
      )((id) => Journal(id.getOrElse(0), null))((journal) => Option(Option(journal.id))),
      "year" -> optional(number),
      "article status" -> text
    )((journal, year, statusTxt) => AuthorList(journal, year.getOrElse(0), ArticleStatus.fromString(statusTxt)))((authorList) => Option(authorList.journal, Option(authorList.year), Option(authorList.articleStatus).getOrElse("").toString)))

  def index = withAuth {
    user => implicit request =>
      Ok(html.lists.authors(RankingDataExtractorOjsDao.getListOfAllAuthors(0, 0, null), form, "List of authors", routes.Lists.indexPost()))
  }

  def indexPost = withAuth {
    user => implicit request =>
      form.bindFromRequest.fold(

        ErrorWrapper.getFormErrorWrapper[AuthorList],

        authorList => {
          val ojsJournalId = authorList.journal.id
          val year = authorList.year
          val status = authorList.articleStatus

          Ok( html.lists.authors(RankingDataExtractorOjsDao.getListOfAllAuthors(ojsJournalId, year, status), form.fill(authorList), "List of authors", routes.Lists.indexPost()))
        }
      )
  }

  def reviewers = withAuth {
    user => implicit request =>
      Ok(html.lists.authors(RankingDataExtractorOjsDao.getListOfAllRewriters(0, 0, null), form,  "List of reviewers", routes.Lists.reviewersPost()))
  }

  def reviewersPost = withAuth {
    user => implicit request =>
      form.bindFromRequest.fold(

        ErrorWrapper.getFormErrorWrapper[AuthorList],

        authorList => {
          val ojsJournalId = authorList.journal.id
          val year = authorList.year
          val status = authorList.articleStatus

          Ok( html.lists.authors(RankingDataExtractorOjsDao.getListOfAllRewriters(ojsJournalId, year, status), form.fill(authorList), "List of reviewers", routes.Lists.reviewersPost()))
        }
      )
  }


}
