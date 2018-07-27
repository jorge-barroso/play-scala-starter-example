package controllers

import javax.inject.{Inject, Singleton}
import models.{Book, Books}
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class BooksController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  val form = Form(mapping(
    "id" -> optional(longNumber),
    "title" -> nonEmptyText,
    "price" -> number(strict = true),
    "author" -> nonEmptyText
  )(Book.apply)(Book.unapply))

  def index = Action { implicit request =>
    Ok(views.html.Books.index(Books.books))
  }

  def create = Action { implicit request =>
    Ok(views.html.Books.create(form))
  }

  def save() = Action { implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors.errors)
        BadRequest(views.html.Books.create(formWithErrors))
      },
      book => {
        val saved = Books.add(book)
        println(saved)
        Redirect(routes.BooksController.index())
      }
    )
  }

  def edit(id: Long) = Action { implicit request =>
    val book = Books.findById(id)
    if(book==null) NotFound("Book not found") else
    {
      val filledForm = form.fill(book)
      println(filledForm)
      Ok(views.html.Books.update(filledForm))
    }
  }

  def update = Action { implicit request =>
    val requestForm = form.bindFromRequest.get
    val oldBook = Books.findById(requestForm.id.get)
    if(oldBook == null) NotFound("Book not found") else {
      oldBook.author = requestForm.author
      oldBook.price = requestForm.price
      oldBook.title = requestForm.title
      Redirect(routes.BooksController.index())
    }
  }

  def destroy(id: Long) = Action {
   val book = Books.findById(id)
    if (book==null) NotFound("Book not found") else {
      Books.remove(book)
      Redirect(routes.BooksController.index())
    }
  }

  def show(id: Long) = Action { implicit request =>
    val book = Books.findById(id)
    if (book == null) NotFound("Book not found") else  {
      Ok(views.html.Books.show(book))
    }
  }
}
