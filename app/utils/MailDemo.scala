package utils

import utils.MailSender._

object MailDemo {

  def test = {
    send a new Mail(
      from = ("ala.ala.ala.ala44@gmail.com", "Ala Ala"),
      to = "arvaladzepl@gmail.com",
      subject = "Subject",
      message = "plain text"
    )

//    send a new Mail(
//      from = ("ala.ala.ala.ala44@gmail.com", "Ala Ala"),
//      to = "arvaladzepl@gmail.com",
//      subject = "Subject",
//      message = "plain text",
//      richMessage = "Html <strong>text</strong>...",
//      attachment = new java.io.File("/home/boss/file")
//    )

    "Mail was sent"
  }


}
