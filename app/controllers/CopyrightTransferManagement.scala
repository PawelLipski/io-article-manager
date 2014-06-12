package controllers


import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import dao.{GeneralOjsDao, CopyrightTransferInternalDao}
import utils.PdfGenerator
import views.html

object CopyrightTransferManagement extends Controller with Secured {

  val consentReportForm = Form(
    tuple(
      "journalId" -> number,
      "author" -> seq(number)
    )
  )

  def list(id: Int, year: Int, volumeId: Int) = withAuth {
    user => implicit request =>

      val transferRequests = CopyrightTransferInternalDao.listTransferRequestsByJournalAndVolume(id, year, volumeId)
      val journals = GeneralOjsDao.getListOfJournals

      Ok(views.html.copyright.management(id, year, volumeId, transferRequests, journals))
  }

  def generateReport = withAuth {
    user => implicit request =>
      consentReportForm.bindFromRequest.fold(
        errors => BadRequest(html.errors.badRequest("Error parsing PDF generation request. Try again.")),
        formData => {
          Ok(PdfGenerator.generate(CopyrightTransferInternalDao.fetchAllTransferRequests(formData._2), formData._1)).as("application/pdf")
        }
      )
  }
}
