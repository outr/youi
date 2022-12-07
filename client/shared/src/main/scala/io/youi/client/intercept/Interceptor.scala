package io.youi.client.intercept

import cats.effect.IO
import io.youi.http.{HttpRequest, HttpResponse}

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration
import scala.util.Try

trait Interceptor {
  def before(request: HttpRequest): IO[HttpRequest]

  def after(request: HttpRequest, result: Try[HttpResponse]): IO[Try[HttpResponse]]
}

object Interceptor {
  object empty extends InterceptorAdapter

  def apply(interceptors: Interceptor*): Interceptor = MultiInterceptor(interceptors.toList)

  def rateLimited(perRequestDelay: FiniteDuration): Interceptor = RateLimiter(perRequestDelay)
}