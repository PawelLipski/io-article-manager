package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import models.{RankingDataExtractorOjsDao, Author}
import scala.collection.mutable.HashMap
import models.dao.CopyrightTransferInternalDao

object Authors extends Controller with Secured {

  def list(id: Int, year: Int, volumeId: Int) = withAuth {
    val transferRequests = CopyrightTransferInternalDao.listTransferRequests(id, year, volumeId)
    val journals = RankingDataExtractorOjsDao.getListOfJournals

    user => implicit request =>
      Ok(views.html.authors.list(id, year, volumeId, transferRequests, journals))
  }
}
