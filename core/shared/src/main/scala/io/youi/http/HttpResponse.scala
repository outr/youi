package io.youi.http

case class HttpResponse(status: Status = Status.OK,
                        headers: Headers = Headers.empty,
                        content: Option[Content] = None) {
  def withHeader(header: Header): HttpResponse = copy(headers = headers.withHeader(header))
  def withHeader(key: String, value: String): HttpResponse = copy(headers = headers.withHeader(key, value))
  def withContent(content: Content): HttpResponse = copy(content = Some(content))
}