package controllers

import play.api.mvc._
import java.sql.{ResultSet, DriverManager}

object DbTest extends Controller {

  private def testOjsDbConnection = {
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

  private def testInternalDbConnection = {
    val passwd = System.getenv("OJS_DB_PASSWD")
    val connStr = "jdbc:mysql://sql.udl.pl:3306/slonka_io?user=slonka_ojs&password=" + passwd
    var contents = ""

    val loadDriver = classOf[com.mysql.jdbc.Driver]

    val conn = DriverManager.getConnection(connStr)
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
    implicit request =>
      Ok(views.html.debug.dbtest(testOjsDbConnection, testInternalDbConnection))
  }

}
