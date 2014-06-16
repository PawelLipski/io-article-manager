package utils

import java.sql.DriverManager

object TokenGenerator {

  def generate: String = {
    val uuid = java.util.UUID.randomUUID.toString
    uuid.replace("-", "")
  }

  def toSha(token: String): String = {
    val md = java.security.MessageDigest.getInstance("SHA-1")
    md.digest(token.getBytes("UTF-8")).map("%02x".format(_)).mkString
  }

}
