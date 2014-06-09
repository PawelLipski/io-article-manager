package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.reports.Journal
import models.reports.Report
import views.html
import models.RankingDataExtractorOjsDao
import utils.ErrorWrapper

object ReportGenerator extends Controller with Secured {
  val form: Form[Report] = Form(
    mapping(
      "journal" -> mapping(
        "id" -> number
      )((id) => Journal(id, null))((journal) => Some(journal.id)),
      "year" -> number(min = 2013, max = 2020)
    )(Report.apply)(Report.unapply)
  )

  def index = withAuth {
    user => implicit request =>
      Ok(html.ranking.index(form))
  }

  def submit = withAuth {
    user => implicit request =>
      form.bindFromRequest.fold(

        ErrorWrapper.getFormErrorWrapper[Report],

        ranking => {
          val ojsJournalId = ranking.journal.id
          val year = ranking.year

          val percentOfForeignAuthors = RankingDataExtractorOjsDao.getPercentOfForeignAuthors(ojsJournalId, year)
          val percentOfForeignReviewers = RankingDataExtractorOjsDao.getPercentOfForeignReviewers(ojsJournalId, year)
          val numberOfPublishedArticles = RankingDataExtractorOjsDao.getNumberOfPublishedArticles(ojsJournalId, year)
          val listOfUnknownAuthors = RankingDataExtractorOjsDao.getListOfUnknownAuthors(ojsJournalId, year)
          val listOfUnknownReviewers = RankingDataExtractorOjsDao.getListOfUnknownReviewers(ojsJournalId, year)

          Ok(html.ranking.report(form.fill(ranking), percentOfForeignAuthors, percentOfForeignReviewers, numberOfPublishedArticles, listOfUnknownAuthors, listOfUnknownReviewers))
        }
      )
  }

}
