package io.youi.communication

import profig.JsonUtil

case class CommunicationMessage(messageType: Int,
                                endPoint: String,
                                invocationId: Int,
                                content: List[String],
                                error: Option[String]) {
  lazy val parsableString: String = try {
    val json = JsonUtil.toJsonString(this)
    s"|CM|$json"
  } catch {
    case t: Throwable => throw new RuntimeException(s"Unable to convert $this to JSON", t)
  }
}

object CommunicationMessage {
  val MethodRequest = 1
  val MethodResponse = 2
  val SharedVariable = 3

  def unapply(unparsedMessage: String): Option[CommunicationMessage] = unparsedMessage match {
    case m if m.startsWith("|CM|") => Some(JsonUtil.fromJsonString[CommunicationMessage](m.substring(4)))
    case _ => None
  }
}