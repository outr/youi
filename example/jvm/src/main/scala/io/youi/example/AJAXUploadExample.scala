package io.youi.example

import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.net.ContentType
import io.youi.server.handler.HttpHandler
import scribe.Execution.global

import scala.concurrent.Future

object AJAXUploadExample extends HttpHandler {
  override def handle(connection: HttpConnection): Future[HttpConnection] = Future {
    connection.request.content match {
      case Some(content) => {
        scribe.info(s"RECEIVED: $content")
        connection.modify { response =>
          response.withContent(Content.string("Received", ContentType.`text/plain`))
        }
      }
      case None => {
        scribe.info("Nothing received!")
        connection.modify { response =>
          response.withStatus(HttpStatus.NoContent).withContent(Content.string("No content sent", ContentType.`text/plain`))
        }
      }
    }
  }
}