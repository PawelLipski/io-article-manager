package controllers

import play.api._
import play.api.mvc._
import java.sql.{ DriverManager, ResultSet}

object Application extends Controller {

  private def testMySQL = {
    val passwd = System.getenv("OJS_DB_PASSWD")
    val connStr = "jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?user=slonka_ojs&password=" + passwd
    var contents = ""

    val loadDriver = classOf[com.mysql.jdbc.Driver]

    val conn = DriverManager.getConnection(connStr)
    try {
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      val rs = statement.executeQuery("SELECT * FROM journals")

      while (rs.next)
        contents += rs.getString("path") + " "

    } finally {
      conn.close()
    }
    contents
  }

  def testPostgreSQL = {

    val passwd = System.getenv("INTERNAL_DB_PASSWD")
    val user = "zlamwfnyrreuew"

    // https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java#connecting-to-a-database-remotely
    val optionalSslSuffix =
      if (Play.isDev(Play.current))
       "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
      else
        ""
    val url = "jdbc:postgresql://ec2-54-246-101-204.eu-west-1.compute.amazonaws.com:5432/d9ml8v51vhnu5u"
    val connStr = url + optionalSslSuffix

    var contents = ""

    val loadDriver = classOf[org.postgresql.Driver]

    val conn = DriverManager.getConnection(connStr, user, passwd)
    try {
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      val rs = statement.executeQuery("SELECT * FROM authors")
      while (rs.next)
        contents += rs.getString("email") + " "

    } finally {
      conn.close()
    }
    contents
  }

  def index = Action {


    // tests should be moved to another module
    var contents = "Your new application is ready. So let's start coding, John Doe! "

    contents += "MySQL: "
    contents += testMySQL

    contents += "PgSQL: "
    contents += testPostgreSQL

    Ok(views.html.index(contents))
  }

}

