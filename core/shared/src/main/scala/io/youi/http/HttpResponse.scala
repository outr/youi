package io.youi.http

case class HttpResponse(status: Status = Status.OK,
                        headers: Headers = Headers.empty,
                        content: Option[Content] = None) {
  lazy val cookies = Headers.Response.`Set-Cookie`.value(headers)

  def withHeader(header: Header): HttpResponse = copy(headers = headers.withHeader(header))
  def withHeader(key: String, value: String): HttpResponse = copy(headers = headers.withHeader(key, value))
  def setHeader(header: Header): HttpResponse = copy(headers = headers.setHeader(header))
  def withContent(content: Content): HttpResponse = copy(content = Some(content))
}