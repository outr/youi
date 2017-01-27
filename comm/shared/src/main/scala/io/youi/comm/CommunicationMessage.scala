package io.youi.comm

case class CommunicationMessage(id: Int, content: Option[String]) {
  lazy val parsableString: String = s"$id:${content.getOrElse("")}"
}

object CommunicationMessage {
  private val MessageRegex = """(\d+):(.*)""".r

  def unapply(unparsedMessage: String): Option[CommunicationMessage] = unparsedMessage match {
    case MessageRegex(id, message) => {
      val messageOption = if (message.nonEmpty) Some(message) else None
      Some(CommunicationMessage(id.toInt, messageOption))
    }
    case _ => None
  }
}