package controllers

import javax.inject.{Inject, Singleton}
import models.{Book, Books}
import play.api.data.Forms._
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.data._

@Singleton
class BooksController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.Books.index(Books.books))
  }

  def create = Action {
    val form = Form(mapping(
      "title" -> text,
      "price" -> number,
      "author" -> text
    )(Book.apply)(Book.unapply))
    Ok("")
  }

  def save = TODO

  def edit(id: Int) = TODO

  def update = TODO

  def destroy(id: Int) = TODO

  def show(id: Int) = TODO
}
