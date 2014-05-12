package utils

import java.sql.DriverManager

object TokenGenerator {

  def generate(): String = {
    val uuid = java.util.UUID.randomUUID.toString
    uuid.replace("-", "")
  }

  def toSHA(token: String): String = {
    val md = java.security.MessageDigest.getInstance("SHA-1")
    md.digest(token.getBytes("UTF-8")).map("%02x".format(_)).mkString
  }

  def generateAndSave(mail: String): String = {
    val token = generate()
    saveToDB(mail, toSHA(token))
    token
  }

  def saveToDB(mail: String, sha: String) {
    val passwd = System.getenv("INTERNAL_DB_PASSWD")
    val connStr = "jdbc:mysql://sql.udl.pl:3306/slonka_io?user=slonka_ojs&password=" + passwd

    val loadDriver = classOf[com.mysql.jdbc.Driver]

    val conn = DriverManager.getConnection(connStr)
    try {
      val statement = conn.createStatement()

      val res = statement.executeUpdate(
        "UPDATE CopyrightTransfer " +
          "SET linkTokenSHASum='" + sha +
          "' WHERE correspondingEmail='" + mail + "'")

    } finally {
      conn.close()
    }
  }
}
