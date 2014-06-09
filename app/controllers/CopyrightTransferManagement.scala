package controllers

import play.api.mvc._
import dao.{GeneralOjsDao, CopyrightTransferInternalDao}

object CopyrightTransferManagement extends Controller with Secured {

  def list(id: Int, year: Int, volumeId: Int) = withAuth {
    user => implicit request =>

      val transferRequests = CopyrightTransferInternalDao.listTransferRequests(id, year, volumeId)
      val journals = GeneralOjsDao.getListOfJournals

      Ok(views.html.copyright.management(id, year, volumeId, transferRequests, journals))
  }
}
