package io.youi.communication

object MessageType {
  case object Invoke extends MessageType {
    override def name: String = "invoke"
  }
  case object Response extends MessageType {
    override def name: String = "response"
  }
  case object Error extends MessageType {
    override def name: String = "error"
  }

  def byName(name: String): MessageType = name match {
    case "invoke" => Invoke
    case "response" => Response
    case "error" => Error
  }
}

sealed trait MessageType {
  def name: String
}