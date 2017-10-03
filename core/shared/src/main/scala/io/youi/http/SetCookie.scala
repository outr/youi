package io.youi.http

import io.youi.http.cookie.ResponseCookie

object SetCookie extends ListTypedHeaderKey[ResponseCookie] {
  private[http] val KeyValueRegex = """(.+)=(.*)""".r
  private val ExpiresRegex = """Expires=(.+)""".r
  private val MaxAgeRegex = """Max-Age=(\d+)""".r
  private val DomainRegex = """Domain=(.+)""".r
  private val PathRegex = """Path=(.+)""".r

  override def key: String = "Set-Cookie"
  override protected def commaSeparated: Boolean = false

  override def value(headers: Headers): List[ResponseCookie] = {
    headers.get(this).map { headerValue =>
      val list = headerValue.split(';').map(_.trim).toList
      val (name, value) = list.head match {
        case KeyValueRegex(k, v) => k -> v
      }
      var cookie = ResponseCookie(name, value)
      list.tail.foreach {
        case ExpiresRegex(date) => cookie = cookie.copy(expires = Some(DateHeaderKey.parser.parse(date).getTime))
        case MaxAgeRegex(seconds) => cookie = cookie.copy(maxAge = Some(seconds.toLong))
        case DomainRegex(domain) => cookie = cookie.copy(domain = Some(domain))
        case PathRegex(path) => cookie = cookie.copy(path = Some(path))
        case "Secure" => cookie = cookie.copy(secure = true)
        case "HttpOnly" => cookie = cookie.copy(httpOnly = true)
      }
      cookie
    }
  }

  override def apply(value: ResponseCookie): Header = Header(this, value.toHTTP())
}
