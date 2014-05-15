package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.reports.{ArticleStatus, AuthorList, Journal}
import views.html
import models.RankingDataExtractorOjsDao

object Lists extends Controller {
  val form: Form[AuthorList] = Form(
    mapping(
      "journal" -> mapping(
        "id" -> optional(number)
      )((id) => Journal(id.getOrElse(0), null))((journal) => Option(Option(journal.id))),
      "year" -> optional(number),
      "article status" -> text
    )((journal, year, statusTxt) => AuthorList(journal, year.getOrElse(0), ArticleStatus.fromString(statusTxt)))((authorList) => Option(authorList.journal, Option(authorList.year), Option(authorList.articleStatus).getOrElse("").toString)))

  def index = Action {
    implicit request =>
      Ok(html.lists.authors(RankingDataExtractorOjsDao.getListOfAllAuthors(0, 0, null), form, "List of Authors", routes.Lists.indexPost()))
  }

  def indexPost = Action {
    implicit request =>
      form.bindFromRequest.fold(
        errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again.\n"+errors.errors),
        authorList => {
          val ojsJournalId = authorList.journal.id
          val year = authorList.year
          val status = authorList.articleStatus

          Ok( html.lists.authors(RankingDataExtractorOjsDao.getListOfAllAuthors(ojsJournalId, year, status), form.fill(authorList), "List of Authors", routes.Lists.indexPost()))
        }
      )
  }

  def reviewers = Action {
    implicit request =>
      Ok(html.lists.authors(RankingDataExtractorOjsDao.getListOfAllRewriters(0, 0, null), form,  "List of reviewers", routes.Lists.reviewersPost()))
  }

  def reviewersPost = Action {
    implicit request =>
      form.bindFromRequest.fold(
        errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again.\n"+errors.errors),
        authorList => {
          val ojsJournalId = authorList.journal.id
          val year = authorList.year
          val status = authorList.articleStatus

          Ok( html.lists.authors(RankingDataExtractorOjsDao.getListOfAllRewriters(ojsJournalId, year, status), form.fill(authorList), "List of reviewers", routes.Lists.reviewersPost()))
        }
      )
  }


}
