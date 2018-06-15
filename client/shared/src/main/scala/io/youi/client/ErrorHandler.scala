package io.youi.client

import io.youi.http.{HttpRequest, HttpResponse}

trait ErrorHandler[Response] {
  def apply(request: HttpRequest, response: HttpResponse, throwable: Option[Throwable]): Response
}