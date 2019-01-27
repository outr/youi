package io.youi.server.handler

import io.youi.Priority
import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus, StringHeaderKey}

import scala.concurrent.Future

trait HttpHandler extends Ordered[HttpHandler] {
  def priority: Priority = Priority.Normal

  def handle(connection: HttpConnection): Future[HttpConnection]

  override def compare(that: HttpHandler): Int = priority.compare(that.priority)
}

object HttpHandler {
  def redirect(connection: HttpConnection, location: String): HttpConnection = {
    val isStreaming = connection.request.headers.first(new StringHeaderKey("streaming")).contains("true")
    if (isStreaming) {
      val status = HttpStatus.NetworkAuthenticationRequired(s"Redirect to $location")
      connection.modify(_.withStatus(status).withContent(Content.empty))
    } else {
      connection.modify(_.withRedirect(location))
    }
    connection.finish()
  }
}