import play.api.db.DB
import play.api.GlobalSettings
// Use H2Driver to connect to an H2 database
import scala.slick.driver.H2Driver.simple._

// Use the implicit threadLocalSession

import play.api.Application
import play.api.Play.current


object Global extends GlobalSettings {


  override def onStart(app: Application) {
    val passwd = System.getenv("OJS_DB_PASSWD")
    val url = "jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?user=slonka_ojs&password=" + passwd
    val jdbcDriver = "com.mysql.jdbc.Driver"
    val slickDriver = "scala.slick.driver.MySQLDriver"

    val outputFolder = "gen/"
    val pkg = "slick"
    val loadDriver = classOf[com.mysql.jdbc.Driver]

    scala.slick.model.codegen.SourceCodeGenerator.main(
      Array(slickDriver, jdbcDriver, url, outputFolder, pkg)
    )

  }
}