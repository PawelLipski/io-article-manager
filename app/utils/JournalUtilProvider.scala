package utils

import com.itextpdf.text.Image
import play.api.Play
import scala.collection.mutable.ListBuffer
import java.io.{InputStreamReader, BufferedReader}
import java.util

/**
 * Created by Zeuko on 31.05.14.
 */
object JournalUtilProvider {

  def getLogoAssetsPath(journalID: Long): String = {
    return "/assets/resources/" + journalID + "/logo.png"
  }

  def getLogoImage(journalID: Long): Image = {
    return Image.getInstance("./public/resources/" + journalID + "/logo.png")
  }

  def getConsentToPublishText(journalID: Long): List[String] = {
    return scala.io.Source.fromFile("./public/resources/" + journalID + "/ctp.txt").getLines().toList
  }

}
