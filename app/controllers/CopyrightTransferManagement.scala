package controllers


import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import dao.{GeneralOjsDao, CopyrightTransferInternalDao}
import utils.{JournalUtilProvider, PdfGenerator}
import views.html
import models.RankingDataExtractorOjsDao

object CopyrightTransferManagement extends Controller with Secured {

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

    var authorsSlick = CopyrightTransferInternalDao.listTransferRequestsByJournalAndVolume(v_id, v_year, v_volume_id)
    var journals = GeneralOjsDao.getListOfJournals
    var yearsActive = GeneralOjsDao.getYearsJournalActive(v_id)
    var issues = GeneralOjsDao.getIssuesForJournal(v_id)

    user => implicit request =>
      Ok(views.html.copyright.management(v_id, v_year, v_volume_id, authorsSlick, journals, yearsActive, issues))
  }

  def removeTransfer(id: Int) = withAuth {
    CopyrightTransferInternalDao.removeTransferRequest(id);

    user => implicit request =>
      Ok(views.html.copyright.transferRemoved())
  }

  def generateReport = withAuth {
    user => implicit request =>
      consentReportForm.bindFromRequest.fold(
        errors => BadRequest(html.errors.badRequest("Error parsing PDF generation request. Try again.")),
        formData => {
          val transferIds = CopyrightTransferInternalDao.fetchAllTransferRequestsByTransferIds(formData._2)
          Ok(PdfGenerator.generate(transferIds, formData._1)).as("application/pdf")
        }
      )
  }
}
