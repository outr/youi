package io.youi.server.handler

import io.youi.Priority
import io.youi.http.{HttpRequest, HttpResponse}

trait HttpHandler extends Ordered[HttpHandler] {
  def priority: Priority = Priority.Normal

  def handle(request: HttpRequest, response: HttpResponse): HttpResponse

  override def compare(that: HttpHandler): Int = priority.compare(that.priority)
}
