package io.youi.communication

import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}

sealed trait MessageType {
  def name: String
  def running: Boolean
}

object MessageType {
  implicit val encoder: Encoder[MessageType] = new Encoder[MessageType] {
    override def apply(a: MessageType): Json = Json.fromString(a.name)
  }
  implicit val decoder: Decoder[MessageType] = new Decoder[MessageType] {
    override def apply(c: HCursor): Result[MessageType] = {
      val s = c.value.asString.getOrElse(throw new RuntimeException(s"Invalid JSON: ${c.value}"))
      Right(byName(s))
    }
  }

  case object Invoke extends MessageType {
    override def name: String = "invoke"
    override def running: Boolean = true
  }
  case object Response extends MessageType {
    override def name: String = "response"
    override def running: Boolean = false
  }
  case object UploadStart extends MessageType {
    override def name: String = "uploadStart"
    override def running: Boolean = true
  }
  case object UploadComplete extends MessageType {
    override def name: String = "uploadComplete"
    override def running: Boolean = false
  }
  case object Error extends MessageType {
    override def name: String = "error"
    override def running: Boolean = false
  }

  def byName(name: String): MessageType = name match {
    case "invoke" => Invoke
    case "response" => Response
    case "uploadStart" => UploadStart
    case "uploadComplete" => UploadComplete
    case "error" => Error
  }
}