package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import models.{RankingDataExtractorOjsDao, Author}
import scala.collection.mutable.HashMap
import models.dao.{GeneralOjsDao, CopyrightTransferInternalDao}
import utils.PdfGenerator

object Authors extends Controller with Secured {

  val consentReportForm = Form(
    tuple(
      "journalId" -> number,
      "author" -> seq(number)
    )
  )

  def list(id: Int, year: Int, volume_id: Int) = withAuth {
    var authorsSlick = CopyrightTransferInternalDao.listTransfer(id, year, volume_id)
    var journals = RankingDataExtractorOjsDao.getListOfJournals
    var yearsActive = GeneralOjsDao.getYearsJournalActive(id)
    var issues = GeneralOjsDao.getIssuesForJournal(volume_id)

    user => implicit request =>
      Ok(views.html.authors.list(id, year, volume_id, authorsSlick, journals, yearsActive, issues))
  }

  def generateReport = withAuth {
    user => implicit request =>
      consentReportForm.bindFromRequest.fold(
        errors => BadRequest("Error parsing PDF generation request. Try again."),
        formData => {
          Ok(PdfGenerator.generate(CopyrightTransferInternalDao.listTransfer(formData._2), formData._1)).as("application/pdf")
        }
      )
  }
}
