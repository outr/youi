package io.youi.server

import io.youi.http.{HttpRequest, HttpResponse}

trait ErrorHandler {
  def handle(request: HttpRequest, response: HttpResponse, t: Option[Throwable]): HttpResponse
}
