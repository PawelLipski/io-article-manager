package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import models.Author
import scala.collection.mutable.HashMap
import models.dao.CopyrightTransferInternalDao

object Authors extends Controller with Secured {

  val authors: scala.collection.mutable.Map[Int, Author] = new HashMap

  val form = Form(
    mapping(
      "papertitle" -> text,
      "username" -> text,
      "lastname" -> text,
      "affiliation" -> text,
      "email" -> text,
      "date_filled" -> text,
      "place" -> text)
      (Author.apply)(Author.unapply)
  )

  def list(id: Int, year: Int, volume_id: Int) = withAuth {
    var authorsSlick = CopyrightTransferInternalDao.listTransfer(id, year, volume_id)

    user => implicit request =>
      Ok(views.html.authors.list(authorsSlick))
  }
}
