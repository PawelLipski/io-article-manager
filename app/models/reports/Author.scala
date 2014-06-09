package models.reports

case class Author(username: String,
                  lastname: String,
                  papertitle: String,
                  affiliation: String,
                  date_filled: String,
                  email: String,
                  place: String) {
  var id: Int = Author.nextId
}

object Author {

  private var currentId = 0

  def nextId: Int = {
    currentId += 1
    currentId
  }
}