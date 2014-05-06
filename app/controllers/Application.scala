package controllers

import play.api._
import play.api.mvc._
import java.sql.{Connection, DriverManager, ResultSet}

object Application extends Controller {

  private def mysql_test = {
    val passwd = System.getenv("OJS_DB_PASSWD")
    val conn_str = "jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?user=slonka_ojs&password=" + passwd
    var contents = ""

    classOf[com.mysql.jdbc.Driver]

    val conn = DriverManager.getConnection(conn_str)
    try {
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      val rs = statement.executeQuery("SELECT * FROM journals")

      while (rs.next) {
        contents += rs.getString("path") + " "
      }

    } finally {
      conn.close()
    }
    contents
  }

  def pgsql_test = {

    val passwd = System.getenv("INTERNAL_DB_PASSWD")
    val user = "zlamwfnyrreuew"

    // for non-Heroku deployment, the following config is necessary
    // don't use in production!
    // https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java#connecting-to-a-database-remotely
    val opt_ssl_suffix =
      if (Play.isDev(Play.current))
       "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
      else
        ""
    val url = "jdbc:postgresql://ec2-54-246-101-204.eu-west-1.compute.amazonaws.com:5432/d9ml8v51vhnu5u"
    val conn_str = url + opt_ssl_suffix

    var contents = ""

    classOf[org.postgresql.Driver]

    val conn = DriverManager.getConnection(conn_str, user, passwd)
    try {

      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      val rs = statement.executeQuery("SELECT * FROM authors")

      while (rs.next) {
        contents += rs.getString("email") + " "
      }
    } finally {
      conn.close()
    }
    contents
  }

  def index = Action {

    var contents = "I`m ready. So let's start coding, John Slon! "

    contents += "MySQL: "
    contents += mysql_test

    contents += "PgSQL: "
    contents += pgsql_test

    Ok(views.html.index(contents))
  }



}

