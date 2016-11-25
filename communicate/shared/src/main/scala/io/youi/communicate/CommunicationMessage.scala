package io.youi.communicate

case class CommunicationMessage(id: Int, invocationId: Int, invocationType: InvocationType, content: Option[String]) {
  lazy val parsableString: String = s"$id:$invocationId:${invocationType.id}:${content.getOrElse("")}"
}

object CommunicationMessage {
  private val MessageRegex = """(\d+):(\d+):(\d+):(.*)""".r

  def unapply(unparsedMessage: String): Option[CommunicationMessage] = unparsedMessage match {
    case MessageRegex(messageId, invocationId, invocationType, message) => {
      val messageOption = if (message.nonEmpty) Some(message) else None
      Some(CommunicationMessage(messageId.toInt, invocationId.toInt, InvocationType(invocationType.toInt), messageOption))
    }
    case _ => None
  }
}