package io.youi.client

import io.youi.http.content.Content
import io.youi.http.{HttpRequest, HttpResponse}

import scala.concurrent.{ExecutionContext, Future}

abstract class HttpClientImplementation(val config: HttpClientConfig) {
  def send(request: HttpRequest, executionContext: ExecutionContext): Future[HttpResponse]

  def content2String(content: Content): String
}