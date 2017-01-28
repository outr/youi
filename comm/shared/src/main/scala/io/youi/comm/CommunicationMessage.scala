package io.youi.comm

case class CommunicationMessage(communicationId: Int,
                                endPointId: Int,
                                invocationId: Int,
                                content: Option[String]) {
  lazy val parsableString: String = s"$communicationId:$endPointId:$invocationId:${content.getOrElse("")}"
}

object CommunicationMessage {
  private val MessageRegex = """(\d+):(\d+):(\d+):(.*)""".r

  def unapply(unparsedMessage: String): Option[CommunicationMessage] = unparsedMessage match {
    case MessageRegex(communicationId, endPointId, invocationId, message) => {
      val messageOption = if (message.nonEmpty) Some(message) else None
      Some(CommunicationMessage(communicationId.toInt, endPointId.toInt, invocationId.toInt, messageOption))
    }
    case _ => None
  }
}