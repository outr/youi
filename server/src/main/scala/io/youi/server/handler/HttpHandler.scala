package io.youi.server.handler

import io.youi.Priority
import io.youi.http.HttpConnection

trait HttpHandler extends Ordered[HttpHandler] {
  def priority: Priority = Priority.Normal

  def handle(connection: HttpConnection): Unit

  override def compare(that: HttpHandler): Int = priority.compare(that.priority)
}
