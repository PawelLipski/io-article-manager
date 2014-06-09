package controllers

import play.api.mvc._
import models.RankingDataExtractorOjsDao
import models.dao.CopyrightTransferInternalDao

object CopyrightTransferManagement extends Controller with Secured {

  def list(id: Int, year: Int, volumeId: Int) = withAuth {
    val transferRequests = CopyrightTransferInternalDao.listTransferRequests(id, year, volumeId)
    val journals = RankingDataExtractorOjsDao.getListOfJournals

    user => implicit request =>
      Ok(views.html.authors.list(id, year, volumeId, transferRequests, journals))
  }
}
