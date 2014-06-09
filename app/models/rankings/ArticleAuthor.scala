package models.rankings

case class ArticleAuthor(username: String,
                         lastname: String,
                         journal: Journal,
                         article: Article,
                         affiliation: String,
                         email: String) {

}
