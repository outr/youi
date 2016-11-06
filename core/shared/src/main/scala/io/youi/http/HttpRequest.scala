package io.youi.http

import io.youi.net.URL

case class HttpRequest(method: Method = Method.Get,
                       url: URL = URL(),
                       headers: Headers = Headers.empty,
                       content: Option[Content] = None) {
  lazy val cookies = Headers.Request.`Cookie`.value(headers)
  def withHeader(header: Header): HttpRequest = copy(headers = headers.withHeader(header))
  def withHeader(key: String, value: String): HttpRequest = copy(headers = headers.withHeader(key, value))
  def withContent(content: Content): HttpRequest = copy(content = Some(content))
}