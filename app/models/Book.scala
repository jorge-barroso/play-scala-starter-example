package models

import scala.collection.mutable.HashSet

case class Book(id: Option[Long], title: String, price: Int, author: String)

object Books {
  private val _books: HashSet[Book] = HashSet.empty[Book]

  def books = _books

  _books.+=(new Book(Option(1), "C++", 20, "Dennis Ritchie"))
  _books.+=(new Book(Option(2), "Java", 30, "Sun Mycrosystems Inc."))

  def findById(id: Integer): Book = {
    for(book: Book <- books){
      if(id.eq(book.id))
        book
    }

    null
  }

  def add(book: Book) = {
    books.add(book)
  }

  def remove(book: Book): Boolean = {
    books.remove(book)
  }
}