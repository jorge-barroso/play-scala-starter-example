package models

import scala.collection.mutable.HashSet

case class Book(id: Option[Long], var title: String, var price: Int, var author: String)

object Books {
  private val _books: HashSet[Book] = HashSet.empty[Book]

  def books: HashSet[Book] = _books

  _books += Book(Option(1), "C++", 20, "Dennis Ritchie")
  _books += Book(Option(2), "Java", 30, "Sun Microsystems Inc.")

  def findById(id: Long): Book = {
    for(book: Book <- books){
      if(id == book.id.get)
        return book
    }

    null
  }

  def add(book: Book): Boolean = {
    _books.add(book)
  }

  def remove(book: Book): Boolean = {
    _books.remove(book)
  }
}