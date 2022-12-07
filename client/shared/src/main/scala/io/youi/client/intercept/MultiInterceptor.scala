package io.youi.client.intercept

import cats.effect.IO
import io.youi.http.{HttpRequest, HttpResponse}
import scribe.Execution.global

import scala.concurrent.Future
import scala.util.Try

case class MultiInterceptor(interceptors: List[Interceptor]) extends Interceptor {
  override def before(request: HttpRequest): IO[HttpRequest] = beforeRecursive(request, interceptors)

  private def beforeRecursive(request: HttpRequest, list: List[Interceptor]): IO[HttpRequest] = if (list.isEmpty) {
    IO.pure(request)
  } else {
    val interceptor = list.head
    interceptor.before(request).flatMap { updated =>
      beforeRecursive(updated, list.tail)
    }
  }

  override def after(request: HttpRequest, result: Try[HttpResponse]): IO[Try[HttpResponse]] = {
    afterRecursive(request, result, interceptors)
  }

  private def afterRecursive(request: HttpRequest,
                             result: Try[HttpResponse],
                             list: List[Interceptor]): IO[Try[HttpResponse]] = if (list.isEmpty) {
    IO.pure(result)
  } else {
    val interceptor = list.head
    interceptor.after(request, result).flatMap { updated =>
      afterRecursive(request, updated, list.tail)
    }
  }
}
