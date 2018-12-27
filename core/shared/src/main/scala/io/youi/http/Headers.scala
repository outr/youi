package io.youi.http

import io.youi.net.ContentType

import scala.collection.immutable.TreeMap

case class Headers(map: TreeMap[String, List[String]] = TreeMap.empty(Ordering.by(_.toLowerCase))) {
  def first(key: HeaderKey): Option[String] = get(key).headOption
  def get(key: HeaderKey): List[String] = map.getOrElse(key.key, Nil)
  def withHeader(header: Header): Headers = {
    val list = get(header.key)
    copy(map + (header.key.key -> (list ::: List(header.value))))
  }
  def setHeader(header: Header): Headers = {
    copy(map + (header.key.key -> List(header.value)))
  }
  def removeHeader(header: HeaderKey): Headers = {
    copy(map - header.key)
  }
  def withHeader(key: String, value: String): Headers = withHeader(Header(new StringHeaderKey(key), value))
  def withHeaders(key: String, values: List[String]): Headers = copy(map + (key -> values))

  def merge(headers: Headers): Headers = copy(map ++ headers.map)
}

object Headers {
  val empty: Headers = Headers()

  def apply(map: Map[String, List[String]]): Headers = apply(TreeMap[String, List[String]](map.toArray: _*)(Ordering.by(_.toLowerCase)))

  def `Cache-Control`: CacheControl.type = CacheControl
  case object `Connection` extends StringHeaderKey("Connection")
  case object `Content-Length` extends LongHeaderKey("Content-Length")
  case object `Content-Type` extends TypedHeaderKey[ContentType] {
    override def key: String = "Content-Type"
    override protected def commaSeparated: Boolean = false

    override def apply(value: ContentType): Header = Header(this, value.outputString)

    override def value(headers: Headers): Option[ContentType] = headers.first(this).map(ContentType.parse)
  }
  case object `Upgrade` extends StringHeaderKey("Upgrade")

  object Request {
    case object `Accept-Encoding` extends StringHeaderKey("Accept-Encoding")
    case object `Accept-Language` extends StringHeaderKey("Accept-Language")
    case object `Authorization` extends StringHeaderKey("Authorization")
    def `Cookie` = CookieHeader
    case object `If-Modified-Since` extends DateHeaderKey("If-Modified-Since")
    case object `Origin` extends StringHeaderKey("Origin")
    case object `User-Agent` extends StringHeaderKey("User-Agent", commaSeparated = false)
    case object `X-Forwarded-For` extends StringHeaderKey("X-Forwarded-For")
    case object `X-Forwarded-For-Host` extends StringHeaderKey("X-Forwarded-For-Host")
    case object `X-Forwarded-For-Port` extends StringHeaderKey("X-Forwarded-For-Port")
  }
  object Response {
    case object `Cache-Control` extends HeaderKey {
      override def key: String = "Cache-Control"
      override protected def commaSeparated: Boolean = false

      def apply(value: String = "no-cache, max-age=0, must-revalidate, no-store"): Header = {
        Header(this, value)
      }
    }
    def `Set-Cookie` = SetCookie
    case object `Content-Disposition` extends HeaderKey {
      override def key: String = "Content-Disposition"
      override protected def commaSeparated: Boolean = false

      def apply(dispositionType: DispositionType,
                name: Option[String] = None,
                fileName: Option[String] = None): Header = {
        val entries = List(
          Some(dispositionType.value),
          name.map(n => s"""name="$n""""),
          fileName.map(fn => s""" filename="$fn" """)
        ).flatten
        Header(this, entries.mkString("; "))
      }
    }

    case object `Expires` extends DateHeaderKey("Expires")
    case object `Last-Modified` extends DateHeaderKey("Last-Modified")
    case object `Location` extends StringHeaderKey("Location")
    case object `Access-Control-Allow-Origin` extends StringHeaderKey("Access-Control-Allow-Origin")
    case object `Access-Control-Allow-Credentials` extends BooleanHeaderKey("Access-Control-Allow-Credentials")
    case object `Access-Control-Allow-Headers` extends StringHeaderKey("Access-Control-Allow-Headers")
    case object `Access-Control-Allow-Methods` extends StringHeaderKey("Access-Control-Allow-Methods")
  }
}