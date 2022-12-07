package io.youi.client

import cats.effect.IO
import io.youi.http.content.Content
import io.youi.http.{HttpRequest, HttpResponse}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

abstract class HttpClientImplementation(val config: HttpClientConfig) {
  def send(request: HttpRequest): IO[Try[HttpResponse]]

  def content2String(content: Content): String
}