package io.youi.client

import io.youi.http.{HttpRequest, HttpResponse, HttpStatus}

class ClientException(message: String,
                      request: HttpRequest,
                      response: HttpResponse,
                      cause: Option[Throwable]) extends RuntimeException(message, cause.orNull) {
  def status: HttpStatus = response.status
}