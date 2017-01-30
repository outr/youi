package io.youi.communication

case class CommunicationMessage(messageType: Int,
                                endPointId: Int,
                                invocationId: Int,
                                content: List[String]) {
  lazy val parsableString: String = s"$messageType:$endPointId:$invocationId:${upickle.default.write(content)}"
}

object CommunicationMessage {
  val MethodRequest = 1
  val MethodResponse = 2
  val SharedVariable = 3

  private val MessageRegex = """(\d+):(\d+):(\d+):(.*)""".r

  def unapply(unparsedMessage: String): Option[CommunicationMessage] = unparsedMessage match {
    case MessageRegex(messageType, endPointId, invocationId, contentJSON) => {
      val content = upickle.default.read[List[String]](contentJSON)
      Some(CommunicationMessage(messageType.toInt, endPointId.toInt, invocationId.toInt, content))
    }
    case _ => None
  }
}