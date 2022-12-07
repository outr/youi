package io.youi.client.intercept

import cats.effect.IO
import io.youi.http.{HttpRequest, HttpResponse}

import scala.concurrent.Future
import scala.util.Try

abstract class InterceptorAdapter extends Interceptor {
  override def before(request: HttpRequest): IO[HttpRequest] = IO.pure(request)

  override def after(request: HttpRequest, result: Try[HttpResponse]): IO[Try[HttpResponse]] = IO.pure(result)
}