package io.youi.http

import java.text.SimpleDateFormat
import java.util.{Date, Locale}

import scala.util.Try

case class Headers(map: Map[String, List[Header]] = Map.empty) {
  def first(key: HeaderKey): Option[Header] = get(key).headOption
  def get(key: HeaderKey): List[Header] = map.getOrElse(key.key, Nil)
  def withHeader(header: Header): Headers = {
    val list = get(header.key)
    copy(map + (header.key.key -> (list ::: List(header))))
  }
  def setHeader(header: Header): Headers = {
    copy(map + (header.key.key -> List(header)))
  }
  def withHeader(key: String, value: String): Headers = withHeader(Header(new StringHeaderKey(key), value))

  def merge(headers: Headers): Headers = copy(map ++ headers.map)
}

object Headers {
  val empty: Headers = Headers()

  case object `Content-Length` extends LongHeaderKey("Content-Length")
  case object `Content-Type` extends StringHeaderKey("Content-Type")
  case object `Cookie` extends StringHeaderKey("Cookie")

  object Request {
    case object `Accept-Encoding` extends StringHeaderKey("Accept-Encoding")
    case object `Accept-Language` extends StringHeaderKey("Accept-Language")
    case object `Authorization` extends StringHeaderKey("Authorization")
    case object `If-Modified-Since` extends DateHeaderKey("If-Modified-Since")
    case object `User-Agent` extends StringHeaderKey("User-Agent")
    case object `X-Forwarded-For` extends StringHeaderKey("X-Forwarded-For")
    case object `X-Forwarded-For-Host` extends StringHeaderKey("X-Forwarded-For-Host")
    case object `X-Forwarded-For-Port` extends StringHeaderKey("X-Forwarded-For-Port")
  }
  object Response {
    case object `Cache-Control` extends HeaderKey {
      override def key: String = "Cache-Control"

      def apply(value: String = "no-cache, max-age=0, must-revalidate, no-store"): Header = {
        Header(this, value)
      }
    }
    case object `Content-Disposition` extends HeaderKey {
      override def key: String = "Content-Disposition"

      def apply(dispositionType: String, fileName: String): Header = {
        Header(this, s"""$dispositionType; filename="$fileName"""")
      }
    }

    case object `Location` extends StringHeaderKey("Location")
  }
}

trait HeaderKey {
  def key: String

  def get(headers: Headers): Option[String] = headers.first(this).map(_.value)
}

trait TypedHeaderKey[V] extends HeaderKey {
  def value(headers: Headers): Option[V]

  def apply(value: V): Header
}

class StringHeaderKey(val key: String) extends TypedHeaderKey[String] {
  override def value(headers: Headers): Option[String] = get(headers)

  override def apply(value: String): Header = Header(this, value)
}

class DateHeaderKey(val key: String) extends TypedHeaderKey[Long] {
  def parser: SimpleDateFormat = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss zzz", Locale.ENGLISH)

  override def value(headers: Headers): Option[Long] = get(headers).map(parser.parse(_).getTime)

  override def apply(date: Long): Header = Header(this, parser.format(new Date(date)))
}

class LongHeaderKey(val key: String) extends TypedHeaderKey[Long] {
  override def value(headers: Headers): Option[Long] = Try(headers.first(this).map(_.value.toLong)).getOrElse(None)

  override def apply(value: Long): Header = Header(this, value.toString)
}

case class Header(key: HeaderKey, value: String)