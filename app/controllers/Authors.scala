package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import models.{RankingDataExtractorOjsDao, Author}
import scala.collection.mutable.HashMap
import models.dao.{GeneralOjsDao, CopyrightTransferInternalDao}
import utils.{JournalUtilProvider, PdfGenerator}

object Authors extends Controller with Secured {

  val consentReportForm = Form(
    tuple(
      "journalId" -> number,
      "author" -> seq(number)
    )
  )

  def list(id: String, year: String, volume_id: String) = withAuth {
    var v_id = JournalUtilProvider.toInt(id);
    var v_year = JournalUtilProvider.toInt(year);
    var v_volume_id = JournalUtilProvider.toInt(volume_id);

    if(id == "default") {
      v_id = CopyrightTransferInternalDao.getDefaultJournalId
    }
    if(year == "default") {
      v_year = CopyrightTransferInternalDao.getDefaultYear
    }
    if(volume_id == "default") {
      v_volume_id = CopyrightTransferInternalDao.getDefaultVolumeId
    } else if(volume_id == "all") {
      v_volume_id = -1;
    }

    var authorsSlick = CopyrightTransferInternalDao.listTransfer(v_id, v_year, v_volume_id)
    var journals = RankingDataExtractorOjsDao.getListOfJournals
    var yearsActive = GeneralOjsDao.getYearsJournalActive(v_id)
    var issues = GeneralOjsDao.getIssuesForJournal(v_volume_id)

    user => implicit request =>
      Ok(views.html.authors.list(v_id, v_year, v_volume_id, authorsSlick, journals, yearsActive, issues))
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
