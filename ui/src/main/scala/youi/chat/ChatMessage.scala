package youi.chat

import youi.component.Component
import youi.image.Image

sealed trait ChatMessage {
  def id: String
  def role: String
  def timestamp: Long
}

object ChatMessage {
  case class Text(
    id: String,
    text: String,
    role: String = "user",
    timestamp: Long = System.currentTimeMillis()
  ) extends ChatMessage

  case class WithImage(
    id: String,
    text: String,
    image: Image,
    role: String = "user",
    timestamp: Long = System.currentTimeMillis()
  ) extends ChatMessage

  case class Rich(
    id: String,
    html: String,
    role: String = "assistant",
    timestamp: Long = System.currentTimeMillis()
  ) extends ChatMessage

  case class Custom(
    id: String,
    role: String,
    factory: () => Component,
    timestamp: Long = System.currentTimeMillis()
  ) extends ChatMessage

  case class Group(
    id: String,
    summary: ChatMessage,
    steps: Vector[ChatMessage],
    role: String = "assistant",
    timestamp: Long = System.currentTimeMillis()
  ) extends ChatMessage

  private var _counter = 0L
  def nextId(): String = {
    _counter += 1
    s"msg-${_counter}"
  }
}
