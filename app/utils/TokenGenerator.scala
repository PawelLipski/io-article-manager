package utils

object TokenGenerator {

  def generate() : String  = {
    val uuid = java.util.UUID.randomUUID.toString
    uuid.replace("-", "")
  }

  def toSHA(token : String) : String = {
    val md = java.security.MessageDigest.getInstance("SHA-1")
    md.digest(token.getBytes("UTF-8")).map("%02x".format(_)).mkString
  }

  def generateAndSave(mail : String) : String = {
    val token = generate()
    saveToDB(mail, toSHA(token))
    token
  }

  def saveToDB(mail : String, sha : String) {

  }

// TEST
//  def main(args: Array[String]) {
//    val token = generate()
//    println(token)
//    println(toSHA(token))
//  }
}
