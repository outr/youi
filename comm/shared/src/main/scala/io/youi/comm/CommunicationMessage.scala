package io.youi.comm

case class CommunicationMessage(messageType: Int,
                                communicationId: Int,
                                endPointId: Int,
                                invocationId: Int,
                                content: List[String]) {
  lazy val parsableString: String = s"$messageType:$communicationId:$endPointId:$invocationId:${upickle.default.write(content)}"
}

object CommunicationMessage {
  val MethodRequest = 1
  val MethodResponse = 2

  private val MessageRegex = """(\d+):(\d+):(\d+):(\d+):(.*)""".r

  def unapply(unparsedMessage: String): Option[CommunicationMessage] = unparsedMessage match {
    case MessageRegex(messageType, communicationId, endPointId, invocationId, contentJSON) => {
      val content = upickle.default.read[List[String]](contentJSON)
      Some(CommunicationMessage(messageType.toInt, communicationId.toInt, endPointId.toInt, invocationId.toInt, content))
    }
    case _ => None
  }
}