package utils

import com.itextpdf.text.Image
import play.api.Play
import play.api.Play.current
import scala.io._
import java.nio.file.{Paths, Path, Files}
import java.io.{File, FileNotFoundException}


/**
 * Created by Zeuko on 31.05.14.
 */
object JournalUtilProvider {

  def getLogoAssetsPath(journalID: Long): String = {
    val path = "/assets/resources/" + journalID + "/logo.png"
    if (!new File(path).exists()) {
      throw new FileNotFoundException("Logo for journal id "+journalID+" is missing.");
    }
    path
  }

  def getLogoImage(journalID: Long): Image = {
    val path = "public/resources/" + journalID + "/logo.png"
    if (new File(path).exists()) {
      Image.getInstance(Play.resource(path).get)
    } else {
      throw new FileNotFoundException("Logo for journal id "+journalID+" is missing.");
    }
  }

  def getConsentToPublishText(journalID: Long): List[String] = {
    val path = "public/resources/" + journalID + "/ctp.txt"
    if (new File(path).exists()) {
      Source.fromInputStream(Play.resourceAsStream(path).get).getLines().toList
    } else {
      throw new FileNotFoundException("Consent text for journal id "+journalID+" is missing.");
    }
  }

}
