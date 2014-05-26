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

  def list(id: Int, year: Int, volume_id: Int) = withAuth {
    var authorsSlick = CopyrightTransferInternalDao.listTransfer(id, year, volume_id)
    var journals = RankingDataExtractorOjsDao.getListOfJournals

    user => implicit request =>
      Ok(views.html.authors.list(id, year, volume_id, authorsSlick, journals))
  }

  def generateReport = withAuth {
    user => implicit request =>
      (request.body).asOpt[List[Int]].map { ctrIds =>
        Ok(PdfGenerator.generate(CopyrightTransferInternalDao.listTransfer(ctrIds))).as("application/pdf")
      }.getOrElse {
        BadRequest("Bad request IDs!")
      }
  }
}
