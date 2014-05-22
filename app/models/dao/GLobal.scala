import java.io.File
import java.nio.file.Path
import play.api.db.DB
import play.api.GlobalSettings
import scala.slick.model.codegen.SourceCodeGenerator
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.JdbcProfile
import scala.reflect.runtime.currentMirror
import play.api.Play.current

import play.api.Application

object Global extends GlobalSettings {


  override def onStart(app: Application) {
    val slickDriver = "scala.slick.driver.MySQLDriver"

    val outputFolder = "gen/app/"
    var pkg = "slick.ojs"

    if (!new File("gen/app").exists) {
      val driver: JdbcProfile = currentMirror.reflectModule(
        currentMirror.staticModule(slickDriver)
      ).instance.asInstanceOf[JdbcProfile]

      Database.forDataSource(DB.getDataSource("ojs")) withSession {
        implicit session =>
          new SourceCodeGenerator(driver.createModel).writeToFile(slickDriver, outputFolder, pkg)
      }
      pkg = "slick.internal"

      Database.forDataSource(DB.getDataSource("internal")) withSession {
        implicit session =>
          new SourceCodeGenerator(driver.createModel).writeToFile(slickDriver, outputFolder, pkg)
      }
    }

  }

}

