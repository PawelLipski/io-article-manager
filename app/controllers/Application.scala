package controllers

import play.api._
import play.api.mvc._
import java.sql.{Connection, DriverManager, ResultSet}
import com.mysql.jdbc.Driver

object Application extends Controller {

  def index = Action {

    var contents = "Your new application is ready. So let's start coding, John Doe! MySQL: ";

    val passwd = System.getenv("OJS_DB_PASSWD")
    val conn_str = "jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?user=slonka_ojs&password=" + passwd

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

    Ok(views.html.index(contents))
  }

}

