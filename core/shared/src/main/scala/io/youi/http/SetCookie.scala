package io.youi.http

import io.youi.http.cookie.{ResponseCookie, SameSite}

object SetCookie extends ListTypedHeaderKey[ResponseCookie] {
  private[http] val KeyValueRegex = """(.+)=(.*)""".r
  private val ExpiresRegex = """(?i)Expires=(.+)""".r
  private val MaxAgeRegex = """(?i)Max-Age=(\d+)""".r
  private val DomainRegex = """(?i)Domain=(.+)""".r
  private val PathRegex = """(?i)Path=(.+)""".r

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
        case ExpiresRegex(date) => cookie = cookie.copy(expires = DateHeaderKey.parse(date))
        case MaxAgeRegex(seconds) => cookie = cookie.copy(maxAge = Some(seconds.toLong))
        case DomainRegex(domain) => cookie = cookie.copy(domain = Some(domain))
        case PathRegex(path) => cookie = cookie.copy(path = Some(path))
        case s if s.equalsIgnoreCase("Secure") => cookie = cookie.copy(secure = true)
        case s if s.equalsIgnoreCase("HttpOnly") => cookie = cookie.copy(httpOnly = true)
        case s if s.equalsIgnoreCase("SameSite=strict") => cookie = cookie.copy(sameSite = SameSite.Strict)
        case s if s.equalsIgnoreCase("SameSite=lax") => cookie = cookie.copy(sameSite = SameSite.Lax)
      }
      cookie
    }
  }

  override def apply(value: ResponseCookie): Header = Header(this, value.http)
}