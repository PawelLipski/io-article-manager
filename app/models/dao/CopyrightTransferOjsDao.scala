/**
 * Created by Kuba on 2014-05-08.
 */
package models.dao

import play.api._
import play.api.mvc._
import java.sql.{DriverManager, ResultSet}
import models.copyright.Copyright

class CopyrightTransferOjsDao {

  def getAuthorsForArticle(ojsArticleId: Int):String = {

    val passwd = System.getenv("OJS_DB_PASSWD")
    val connStr = "jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?user=slonka_ojs&password=" + passwd
    var contents = ""

    val loadDriver = classOf[com.mysql.jdbc.Driver]

    val conn = DriverManager.getConnection(connStr)
    try {
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
       val napis = "SELECT author_id FROM article_comments WHERE article_comments.article_id = " + ojsArticleId.toString()

      val rs = statement.executeQuery(napis)
      while (rs.next)
        contents += rs.getString("article_id") + " "

    } finally {
      conn.close()
    }
    contents
  }
}
