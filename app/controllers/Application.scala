package controllers

import play.api.mvc._
import models.dao.GeneralOjsDao
object Application extends Controller {

  def index {
    System.out.println(GeneralOjsDao.getYearsJournalActive(4))
    //implicit request =>
    //  Ok(views.html.index())
  }

}

