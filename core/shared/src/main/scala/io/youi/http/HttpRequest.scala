package io.youi.http

import io.youi.http.content.Content
import io.youi.http.cookie.RequestCookie
import io.youi.net.{IP, URL}

case class HttpRequest(method: Method = Method.Get,
                       source: IP = IP.LocalHost,
                       url: URL = URL(),
                       headers: Headers = Headers.empty,
                       content: Option[Content] = None,
                       timestamp: Long = System.currentTimeMillis()) {
  lazy val cookies: List[RequestCookie] = Headers.Request.`Cookie`.value(headers)
  def withHeader(header: Header): HttpRequest = copy(headers = headers.withHeader(header))
  def withHeader(key: String, value: String): HttpRequest = copy(headers = headers.withHeader(key, value))
  def withContent(content: Content): HttpRequest = copy(content = Some(content))
}