package io.youi.client.intercept

import io.youi.http.{HttpRequest, HttpResponse}

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

trait Interceptor {
  def before(request: HttpRequest): Future[HttpRequest]

  def after(request: HttpRequest, response: HttpResponse): Future[HttpResponse]
}

object Interceptor {
  object empty extends InterceptorAdapter

  def apply(interceptors: Interceptor*): Interceptor = MultiInterceptor(interceptors.toList)

  def rateLimited(perRequestDelay: FiniteDuration): Interceptor = RateLimiter(perRequestDelay)
}