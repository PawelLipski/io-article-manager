import play.api.db.DB
import play.api.GlobalSettings
import scala.slick.model.codegen.SourceCodeGenerator
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.{JdbcProfile, MySQLDriver}
import scala.reflect.runtime.currentMirror
import play.api.Play.current

import play.api.Application

object Global extends GlobalSettings {


  override def onStart(app: Application) {
    val slickDriver = "scala.slick.driver.MySQLDriver"

    val outputFolder = "gen/app/internal"
    val pkg = "slick"

    val driver: JdbcProfile = currentMirror.reflectModule(
      currentMirror.staticModule(slickDriver)
    ).instance.asInstanceOf[JdbcProfile]

    Database.forDataSource(DB.getDataSource("internal")) withSession {
      implicit session =>
        new SourceCodeGenerator(driver.createModel).writeToFile(slickDriver, outputFolder, pkg)
    }
  }

}

