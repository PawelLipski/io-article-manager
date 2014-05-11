package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import models.Author
import scala.collection.mutable.HashMap

object Authors extends Controller {

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

  def add = Action {
    Ok(views.html.authors.add(form))
  }

  def save = Action {
    implicit request =>
      val user = form.bindFromRequest.get
      authors.put(user.id, user)
      Redirect(routes.Authors.list)
  }

  def list = Action {
    Ok(views.html.authors.list(authors.values))
  }

  def edit(id: Int) = Action {
    val bindedForm = form.fill(authors.get(id).get)
    Ok(views.html.authors.edit(bindedForm))
  }

  def update(id: Int) = Action {
    implicit request =>
      val user = form.bindFromRequest.get
      user.id = id
      authors.put(id, user)
      Redirect(routes.Authors.list)
  }

  def delete(id: Int) = Action {
    authors.remove(id)
    Redirect(routes.Authors.list)
  }
}