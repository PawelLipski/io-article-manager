package utils

import com.itextpdf.text.Image
import play.api.Play
import play.api.Play.current
import scala.io._


/**
 * Created by Zeuko on 31.05.14.
 */
object JournalUtilProvider {

  def getLogoAssetsPath(journalID: Long): String = {
    "/assets/resources/" + journalID + "/logo.png"
  }

  def getLogoImage(journalID: Long): Image = {
    val path = "public/resources/" + journalID + "/logo.png"
    Image.getInstance(Play.resource(path).get)
  }

  def getConsentToPublishText(journalID: Long): List[String] = {
    val path = "public/resources/" + journalID + "/ctp.txt"
    Source.fromInputStream(Play.resourceAsStream(path).get).getLines().toList
  }

}
