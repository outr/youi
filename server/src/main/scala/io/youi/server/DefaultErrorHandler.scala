package io.youi.server

import io.youi.http.{Headers, HttpRequest, HttpResponse, Status}
import io.youi.net.ContentType

object DefaultErrorHandler extends ErrorHandler {
  override def handle(request: HttpRequest, response: HttpResponse, t: Option[Throwable]): HttpResponse = {
    val status = if (response.status.isError) {
      response.status
    } else {
      Status.InternalServerError
    }
    val html =
      s"""<html>
         |  <head>
         |    <title>Error ${status.code}</title>
         |  </head>
         |  <body>
         |    ${status.code} - ${status.message}
         |  </body>
         |</html>
       """.stripMargin
    response
      .withHeader(Headers.`Content-Length`(html.length))
      .withHeader(Headers.`Content-Type`(ContentType.`text/html`))
      .withContent(html)
  }
}
