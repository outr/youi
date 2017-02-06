package io.youi.http

import io.youi.http.cookie.ResponseCookie

case class HttpResponse(status: Status = Status.OK,
                        headers: Headers = Headers.empty,
                        content: Option[Content] = None) {
  lazy val cookies: List[ResponseCookie] = Headers.Response.`Set-Cookie`.value(headers)

  def withStatus(status: Status): HttpResponse = copy(status = status)
  def withHeader(header: Header): HttpResponse = copy(headers = headers.withHeader(header))
  def withHeader(key: String, value: String): HttpResponse = copy(headers = headers.withHeader(key, value))
  def setHeader(header: Header): HttpResponse = copy(headers = headers.setHeader(header))
  def withContent(content: Content): HttpResponse =
    copy(content = Some(content))
      .withHeader(Headers.`Content-Type`(content.contentType))
      .withHeader(Headers.`Content-Length`(content.length))
  def withRedirect(uri: String): HttpResponse = copy(status = Status.Found).setHeader(Headers.Response.`Location`(uri))
}