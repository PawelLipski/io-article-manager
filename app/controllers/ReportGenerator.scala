package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import org.joda.time.DateTime
import models.copyright.Copyright
import models.reports.Journal
import models.reports.Report
import views.html.helper.options
import views.html
import models.{RankingDataExtractorOjsDao, Author}

object ReportGenerator extends Controller {
  val form: Form[Report] = Form(
    mapping(
      "journal" -> mapping(
        "id" -> number
      )((id) => Journal(id, null))((journal) => Some(journal.id)),
      "year" -> number(min=2013, max = 2020)
    )(Report.apply)(Report.unapply)
  )

  def index = Action {
    Ok(html.ranking.index(form))
  }

  def submit = Action {
    implicit request =>
      form.bindFromRequest.fold(
        errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again.\n"+errors.errors),
        ranking => {
          val ojsJournalId = ranking.journal.id
          val year = ranking.year

          val percentOfForeignAuthors = RankingDataExtractorOjsDao.getPercentOfForeignAuthors(ojsJournalId, year)
          val percentOfForeignReviewers = RankingDataExtractorOjsDao.getPercentOfForeignReviewers(ojsJournalId, year)
          val numberOfPublishedArticles = RankingDataExtractorOjsDao.getNumberOfPublishedArticles(ojsJournalId, year)
          val listOfUnknownAuthors = RankingDataExtractorOjsDao.getListOfUnknownAuthors(ojsJournalId, year)
          val listOfUnknownReviewers= RankingDataExtractorOjsDao.getListOfUnknownReviewers(ojsJournalId, year)

          Ok( html.ranking.report(form.fill(ranking),percentOfForeignAuthors, percentOfForeignReviewers, numberOfPublishedArticles, listOfUnknownAuthors, listOfUnknownReviewers))
        }
      )
  }

}
