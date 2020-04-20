package io.youi.client.intercept

import io.youi.http.{HttpRequest, HttpResponse}
import scribe.Execution.global

import scala.concurrent.Future

case class MultiInterceptor(interceptors: List[Interceptor]) extends Interceptor {
  override def before(request: HttpRequest): Future[HttpRequest] = beforeRecursive(request, interceptors)

  private def beforeRecursive(request: HttpRequest, list: List[Interceptor]): Future[HttpRequest] = if (list.isEmpty) {
    Future.successful(request)
  } else {
    val interceptor = list.head
    interceptor.before(request).flatMap { updated =>
      beforeRecursive(updated, list.tail)
    }
  }

  override def after(request: HttpRequest, response: HttpResponse): Future[HttpResponse] = {
    afterRecursive(request, response, interceptors)
  }

  private def afterRecursive(request: HttpRequest,
                             response: HttpResponse,
                             list: List[Interceptor]): Future[HttpResponse] = if (list.isEmpty) {
    Future.successful(response)
  } else {
    val interceptor = list.head
    interceptor.after(request, response).flatMap { updated =>
      afterRecursive(request, updated, list.tail)
    }
  }
}
