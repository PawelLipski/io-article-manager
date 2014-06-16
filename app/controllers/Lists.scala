package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.rankings.{AuthorListFilter, Report, ArticleStatus, AuthorList, Journal}
import views.html
import models.RankingDataExtractorOjsDao
import utils.ErrorWrapper

object Lists extends Controller with Secured {
  val form: Form[AuthorListFilter] = Form(
    mapping(
      "journal" -> mapping(
        "id" -> optional(number)
      )((id) => if(id.nonEmpty) Option.apply(Journal(id.get, null)) else Option.empty )((journal) => Option(if (journal.nonEmpty) Option(journal.get.id) else Option.empty)),
      "year" -> optional(number),
      "article status" -> optional(text)
    )((journal, year, statusTxt) => AuthorListFilter(journal, year, statusTxt.map(x => ArticleStatus.fromString(x))))((authorList) => Option(authorList.journal, authorList.year, Option(authorList.articleStatus.getOrElse("").toString))))

  def index = withAuth {
    user => implicit request =>
      Ok(html.lists.authors(RankingDataExtractorOjsDao.getListOfAllAuthors(Option.empty, Option.empty, Option.empty), form, "List of authors", routes.Lists.indexPost()))
  }

  def indexPost = withAuth {
    user => implicit request =>
      form.bindFromRequest.fold(

        ErrorWrapper.getFormErrorWrapper[AuthorListFilter],

        authorListFilter => {
          val ojsJournalId = authorListFilter.journal
          val year = authorListFilter.year
          val status = authorListFilter.articleStatus

          Ok( html.lists.authors(RankingDataExtractorOjsDao.getListOfAllAuthors(ojsJournalId.map(_.id), year, status), form.fill(authorListFilter), "List of authors", routes.Lists.indexPost()))
        }
      )
  }

  def reviewers = withAuth {
    user => implicit request =>
      Ok(html.lists.authors(RankingDataExtractorOjsDao.getListOfAllRewriters(Option.empty, Option.empty, Option.empty), form,  "List of reviewers", routes.Lists.reviewersPost()))
  }

  def reviewersPost = withAuth {
    user => implicit request =>
      form.bindFromRequest.fold(

        ErrorWrapper.getFormErrorWrapper[AuthorListFilter],

        authorList => {
          val ojsJournalId = authorList.journal.fold(Option.empty[Int])(x => Option(x.id))
          val year = authorList.year
          val status = authorList.articleStatus

          Ok( html.lists.authors(RankingDataExtractorOjsDao.getListOfAllRewriters(ojsJournalId, year, status), form.fill(authorList), "List of reviewers", routes.Lists.reviewersPost()))
        }
      )
  }


}
