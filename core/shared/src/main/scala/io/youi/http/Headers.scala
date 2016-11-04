package io.youi.http

import java.text.SimpleDateFormat
import java.util.{Date, Locale}

import scala.util.Try

case class Headers(map: Map[String, List[String]] = Map.empty) {
  def first(key: HeaderKey): Option[String] = get(key).headOption
  def get(key: HeaderKey): List[String] = map.getOrElse(key.key, Nil)
  def withHeader(header: Header): Headers = {
    val list = get(header.key)
    copy(map + (header.key.key -> (list ::: List(header.value))))
  }
  def setHeader(header: Header): Headers = {
    copy(map + (header.key.key -> List(header.value)))
  }
  def withHeader(key: String, value: String): Headers = withHeader(Header(new StringHeaderKey(key), value))

  def merge(headers: Headers): Headers = copy(map ++ headers.map)
}

object Headers {
  val empty: Headers = Headers()

  case object `Content-Length` extends LongHeaderKey("Content-Length")
  case object `Content-Type` extends StringHeaderKey("Content-Type")
  def `Cache-Control` = CacheControl

  object Request {
    case object `Accept-Encoding` extends StringHeaderKey("Accept-Encoding")
    case object `Accept-Language` extends StringHeaderKey("Accept-Language")
    case object `Authorization` extends StringHeaderKey("Authorization")
    case object `Cookie` extends StringHeaderKey("Cookie")
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
    case object `Set-Cookie` extends StringHeaderKey("Set-Cookie")
    case object `Content-Disposition` extends HeaderKey {
      override def key: String = "Content-Disposition"

      def apply(dispositionType: String, fileName: String): Header = {
        Header(this, s"""$dispositionType; filename="$fileName"""")
      }
    }

    case object `Expires` extends DateHeaderKey("Expires")
    case object `Last-Modified` extends DateHeaderKey("Last-Modified")
    case object `Location` extends StringHeaderKey("Location")
  }
}

trait HeaderKey {
  def key: String

  def get(headers: Headers): Option[String] = headers.first(this)
}

trait TypedHeaderKey[V] extends HeaderKey {
  def value(headers: Headers): Option[V]

  def apply(value: V): Header
}

trait MultiTypedHeaderKey[V] extends HeaderKey {
  def value(headers: Headers): List[V]

  def apply(values: V*): Header
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
  override def value(headers: Headers): Option[Long] = Try(headers.first(this).map(_.toLong)).getOrElse(None)

  override def apply(value: Long): Header = Header(this, value.toString)
}

case class Header(key: HeaderKey, value: String)

object CacheControl extends MultiTypedHeaderKey[CacheControlEntry] {
  private val MaxAgeRegex = """max-age=(\d+)""".r

  override def key: String = "Cache-Control"

  override def value(headers: Headers): List[CacheControlEntry] = headers.get(this).mkString(", ").split(",").map(_.toLowerCase.trim).map {
    case "public" => Public
    case "private" => Private
    case "no-cache" => NoCache
    case "must-revalidate" => MustRevalidate
    case "no-store" => NoStore
    case MaxAgeRegex(seconds) => MaxAge(seconds.toLong)
  }.toList

  override def apply(values: CacheControlEntry*): Header = Header(this, values.map(_.value).mkString(", "))

  case object Public extends CacheControlEntry {
    override def value: String = "public"
  }
  case object Private extends CacheControlEntry {
    override def value: String = "private"
  }
  case object NoCache extends CacheControlEntry {
    override def value: String = "no-cache"
  }
  case object MustRevalidate extends CacheControlEntry {
    override def value: String = "must-revalidate"
  }
  case object NoStore extends CacheControlEntry {
    override def value: String = "no-store"
  }
  case class MaxAge(seconds: Long) extends CacheControlEntry {
    override def value: String = s"max-age=$seconds"
  }
}

sealed abstract class CacheControlEntry {
  def value: String
}