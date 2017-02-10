package io.youi.communication

case class CommunicationMessage(messageType: Int,
                                endPoint: String,
                                invocationId: Int,
                                content: List[String],
                                error: Option[String]) {
  lazy val parsableString: String = {
    val message = error match {
      case Some(e) => s"0:$e"
      case None => s"1:${upickle.default.write(content)}"
    }
    s"$messageType:[$endPoint]:$invocationId:$message"
  }
}

object CommunicationMessage {
  val MethodRequest = 1
  val MethodResponse = 2
  val SharedVariable = 3

  private val MessageRegex = """(\d+):\[(.+)\]:(\d+):(\d{1}):(.*)""".r

  def unapply(unparsedMessage: String): Option[CommunicationMessage] = unparsedMessage match {
    case MessageRegex(messageType, endPoint, invocationId, success, contentJSON) => {
      val successful = success.toInt == 1
      val (content, error) = if (successful) {
        upickle.default.read[List[String]](contentJSON) -> None
      } else {
        Nil -> Some(contentJSON)
      }
      Some(CommunicationMessage(messageType.toInt, endPoint, invocationId.toInt, content, error))
    }
    case _ => None
  }
}