package io.youi.http

import java.text.SimpleDateFormat
import java.util.{Date, Locale}

case class Headers(map: Map[String, String] = Map.empty) {
  def withHeader(key: String, value: String): Headers = copy(map + (key -> value))

  def merge(headers: Headers): Headers = copy(map ++ headers.map)
}

object Headers {
  val empty: Headers = Headers()

  case object `Cookie` extends StringHeaderKey {
    override def key: String = "Cookie"
  }

  object Request {
    case object `Accept-Encoding` extends StringHeaderKey {
      override def key: String = "Accept-Encoding"
    }
    case object `Accept-Language` extends StringHeaderKey {
      override def key: String = "Accept-Language"
    }
    case object `Authorization` extends StringHeaderKey {
      override def key: String = "Authorization"
    }
    case object `If-Modified-Since` extends DateHeaderKey {
      override def key: String = "If-Modified-Since"
    }
    case object `User-Agent` extends StringHeaderKey {
      override def key: String = "User-Agent"
    }
    case object `X-Forwarded-For` extends StringHeaderKey {
      override def key: String = "X-Forwarded-For"
    }
    case object `X-Forwarded-For-Host` extends StringHeaderKey {
      override def key: String = "X-Forwarded-For-Host"
    }
    case object `X-Forwarded-For-Port` extends StringHeaderKey {
      override def key: String = "X-Forwarded-For-Port"
    }
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

    case object `Location` extends StringHeaderKey {
      override def key: String = "Location"
    }
  }
}

trait HeaderKey {
  def key: String

  def get(headers: Headers): Option[String] = headers.map.get(key)
}

trait TypedHeaderKey[V] extends HeaderKey {
  def value(headers: Headers): Option[V]

  def apply(value: V): Header
}

trait StringHeaderKey extends TypedHeaderKey[String] {
  override def value(headers: Headers): Option[String] = get(headers)

  override def apply(value: String): Header = Header(this, value)
}

trait DateHeaderKey extends TypedHeaderKey[Long] {
  def parser: SimpleDateFormat = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss zzz", Locale.ENGLISH)

  override def value(headers: Headers): Option[Long] = get(headers).map(parser.parse(_).getTime)

  override def apply(date: Long): Header = Header(this, parser.format(new Date(date)))
}

case class Header(key: HeaderKey, value: String)