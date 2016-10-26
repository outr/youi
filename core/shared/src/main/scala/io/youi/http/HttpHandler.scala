package io.youi.http

trait HttpHandler {
  def handle(request: HttpRequest, response: HttpResponse): HttpResponse
}