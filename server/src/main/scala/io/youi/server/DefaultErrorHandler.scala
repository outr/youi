package io.youi.server

import io.youi.http.content.Content
import io.youi.http.{CacheControl, HttpConnection, HttpStatus}
import io.youi.net.ContentType
import io.youi.server.dsl._
import perfolation._

import scala.concurrent.Future

object DefaultErrorHandler extends ErrorHandler {
  lazy val lastModified: Long = System.currentTimeMillis()

  def html(status: HttpStatus): Content = s"""<html>
    <head>
      <title>Error ${status.code}</title>
    </head>
    <body>
      ${status.code} - ${status.message}
    </body>
  </html>""".withContentType(ContentType.`text/html`).withLastModified(lastModified)

  override def handle(connection: HttpConnection, t: Option[Throwable]): Future[HttpConnection] = Future.successful {
    connection.modify { response =>
      val status = if (response.status.isError) {
        response.status
      } else {
        HttpStatus.InternalServerError
      }
      response
        .withContent(html(status))
        .withHeader(CacheControl(CacheControl.NoCache))
    }
  }
}