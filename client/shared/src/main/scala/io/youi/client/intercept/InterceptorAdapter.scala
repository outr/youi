package io.youi.client.intercept

import io.youi.http.{HttpRequest, HttpResponse}

import scala.concurrent.Future

abstract class InterceptorAdapter extends Interceptor {
  override def before(request: HttpRequest): Future[HttpRequest] = Future.successful(request)

  override def after(request: HttpRequest, response: HttpResponse): Future[HttpResponse] = Future.successful(response)
}