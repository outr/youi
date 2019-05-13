package io.youi.http

import io.circe.Decoder.Result
import io.circe.{Decoder, DecodingFailure, Encoder, HCursor, Json}

sealed abstract class HttpMethod private(val value: String) {
  HttpMethod.map += value -> this

  override def toString: String = value
}

object HttpMethod {
  private var map = Map.empty[String, HttpMethod]

  implicit val encoder: Encoder[HttpMethod] = new Encoder[HttpMethod] {
    override def apply(m: HttpMethod): Json = Json.fromString(m.value)
  }
  implicit val decoder: Decoder[HttpMethod] = new Decoder[HttpMethod] {
    override def apply(c: HCursor): Result[HttpMethod] = c.value.asString match {
      case Some(s) => Right(HttpMethod(s))
      case None => Left(DecodingFailure(s"Cannot decode HttpMethod from ${c.value}", Nil))
    }
  }

  val Get: HttpMethod = new HttpMethod("GET") {}
  val Put: HttpMethod = new HttpMethod("PUT") {}
  val Trace: HttpMethod = new HttpMethod("TRACE") {}
  val Connect: HttpMethod = new HttpMethod("CONNECT") {}
  val Head: HttpMethod = new HttpMethod("HEAD") {}
  val Delete: HttpMethod = new HttpMethod("DELETE") {}
  val Patch: HttpMethod = new HttpMethod("PATCH") {}
  val Post: HttpMethod = new HttpMethod("POST") {}
  val Options: HttpMethod = new HttpMethod("OPTIONS") {}

  def get(value: String): Option[HttpMethod] = map.get(value.toUpperCase)
  def apply(value: String): HttpMethod = get(value).getOrElse(throw new RuntimeException(s"$value is an invalid Method."))
}
