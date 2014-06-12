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

object ReportGenerator extends Controller with Secured {
  val form: Form[Report] = Form(
    mapping(
      "journal" -> mapping(
        "id" -> number
      )((id) => Journal(id, null))((journal) => Some(journal.id)),
      "year" -> number
    )(Report.apply)(Report.unapply)
  )

  def index = withAuth {
    user => implicit request =>
      Ok(html.ranking.index(form))
  }

  def submit = withAuth {
    user => implicit request =>
      form.bindFromRequest.fold(
        errors => BadRequest("Unspecified error occurred, nobody knows what happened yet. Try again.\n"+errors.errors),
        ranking => {
          val ojsJournalId = ranking.journal.id
          val year = ranking.year

          val percentOfForeignAuthors = RankingDataExtractorOjsDao.getPercentOfForeignAuthors(ojsJournalId, year)
          val percentOfForeignReviewers = RankingDataExtractorOjsDao.getPercentOfForeignReviewers(ojsJournalId, year)
          val numberOfPublishedArticles = RankingDataExtractorOjsDao.getNumberOfPublishedArticles(ojsJournalId, year)
          val listOfUnknownAuthors = RankingDataExtractorOjsDao.getListOfAuthorsWithUnknownCountry(ojsJournalId, year)
          val listOfUnknownReviewers= RankingDataExtractorOjsDao.getListOfReviewersWithUnknownCountry(ojsJournalId, year)

          Ok( html.ranking.report(form.fill(ranking),percentOfForeignAuthors, percentOfForeignReviewers, numberOfPublishedArticles, listOfUnknownAuthors, listOfUnknownReviewers))
        }
      )
  }

}
