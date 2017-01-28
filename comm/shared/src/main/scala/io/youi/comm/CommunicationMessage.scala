package io.youi.comm

case class CommunicationMessage(communicationId: Int,
                                endPointId: Int,
                                invocationId: Int,
                                content: List[String]) {
  lazy val parsableString: String = s"$communicationId:$endPointId:$invocationId:${upickle.default.write(content)}"
}

object CommunicationMessage {
  private val MessageRegex = """(\d+):(\d+):(\d+):(.*)""".r

  def unapply(unparsedMessage: String): Option[CommunicationMessage] = unparsedMessage match {
    case MessageRegex(communicationId, endPointId, invocationId, contentJSON) => {
      val content = upickle.default.read[List[String]](contentJSON)
      Some(CommunicationMessage(communicationId.toInt, endPointId.toInt, invocationId.toInt, content))
    }
    case _ => None
  }
}