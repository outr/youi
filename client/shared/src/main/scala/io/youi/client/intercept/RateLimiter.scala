package io.youi.client.intercept

import io.youi.http.{HttpRequest, HttpResponse}
import io.youi.util.Time
import scribe.Execution.global

import scala.concurrent.duration._
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

case class RateLimiter(perRequestDelay: FiniteDuration) extends InterceptorAdapter {
  private val maxDelay = perRequestDelay.toMillis
  @volatile private var _lastTime: Long = 0L

  private var future: Future[_] = Future.successful(())

  override def before(request: HttpRequest): Future[HttpRequest] = synchronized {
    val p = Promise[HttpRequest]()
    future.onComplete { _ =>
      val now = System.currentTimeMillis()
      val elapsed = now - _lastTime
      val delay = maxDelay - elapsed
      if (delay > 0L) {
        Time.delay(delay.millis).map(_ => request).onComplete {
          case Success(v) => p.success(v)
          case Failure(exception) => p.failure(exception)
        }
      } else {
        p.success(request)
      }
    }
    val f = p.future
    future = f
    f
  }

  override def after(request: HttpRequest, response: HttpResponse): Future[HttpResponse] = {
    _lastTime = System.currentTimeMillis()

    super.after(request, response)
  }
}