package io.youi.communication

import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}

sealed trait MessageType {
  def name: String
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