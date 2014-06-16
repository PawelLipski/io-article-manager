package controllers

import play.api.mvc._
import models.dao.CopyrightTransferInternalDao

object Application extends Controller {

  def index = Action {
    implicit request =>
      Ok(views.html.index(CopyrightTransferInternalDao.getDefaultJournalId, CopyrightTransferInternalDao.getDefaultYear, CopyrightTransferInternalDao.getDefaultVolumeId))
  }

}

