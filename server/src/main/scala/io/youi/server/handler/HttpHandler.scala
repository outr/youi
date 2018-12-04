package io.youi.server.handler

import io.youi.Priority
import io.youi.http.{Content, HttpConnection, HttpStatus, StringHeaderKey}

trait HttpHandler extends Ordered[HttpHandler] {
  def priority: Priority = Priority.Normal

  def handle(connection: HttpConnection): Unit

  override def compare(that: HttpHandler): Int = priority.compare(that.priority)
}

object HttpHandler {
  def redirect(connection: HttpConnection, location: String): Unit = {
    val isStreaming = connection.request.headers.first(new StringHeaderKey("streaming")).contains("true")
    if (isStreaming) {
      val status = HttpStatus.NetworkAuthenticationRequired(s"Redirect to $location")
      connection.update(_.withStatus(status).withContent(Content.empty))
    } else {
      connection.update(_.withRedirect(location))
    }
    connection.finish()
  }
}