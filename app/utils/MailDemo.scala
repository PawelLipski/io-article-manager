package utils

import utils.MailSender._

object MailDemo {

  def test = {
    send a new Mail(
      from = ("test2@slonka.udl.pl", "Test The Second"),
      to = "test@slonka.udl.pl",
      subject = "Test mail from MailDemo",
      message = "plain text"
//      richMessage = "Html <strong>text</strong>...",
//      attachment = new java.io.File("/home/boss/file")
    )

    "Mail was sent"
  }


}
