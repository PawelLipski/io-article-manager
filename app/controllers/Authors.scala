package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import models.{RankingDataExtractorOjsDao, Author}
import scala.collection.mutable.HashMap
import models.dao.CopyrightTransferInternalDao
import utils.PdfGenerator

object Authors extends Controller with Secured {

  val consentReportForm = Form(
    tuple(
      "journalId" -> number,
      "author" -> seq(number)
    )
  )

  def list(id: String, year: String, volume_id: String) = withAuth {
    var i_id = 0;

    if(id == "default") {


    }

    var authorsSlick = CopyrightTransferInternalDao.listTransfer(id, year, volume_id)
    var journals = RankingDataExtractorOjsDao.getListOfJournals

    user => implicit request =>
      Ok(views.html.authors.list(id, year, volume_id, authorsSlick, journals))
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
