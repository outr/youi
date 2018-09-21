package io.youi.client.intercept

import java.util.concurrent.{Executors, ThreadFactory}

import io.youi.http.{HttpRequest, HttpResponse}

import scala.concurrent.duration._
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

import scribe.Execution.global

case class RateLimiter(perRequestDelay: FiniteDuration) extends InterceptorAdapter {
  private val maxDelay = perRequestDelay.toMillis
  @volatile private var _lastTime: Long = 0L

  private var future: Future[_] = Future.successful(())

  override def before(request: HttpRequest): Future[HttpRequest] = synchronized {
    val f = future.transformWith { _ =>
      val now = System.currentTimeMillis()
      val elapsed = now - _lastTime
      val delay = maxDelay - elapsed
      if (delay > 0L) {
        RateLimiter.delayedFuture(delay.millis, Future.successful(request)).map { _ =>
          _lastTime = System.currentTimeMillis()
          request
        }
      } else {
        _lastTime = System.currentTimeMillis()
        Future.successful(request)
      }
    }
    future = f
    f
  }
}

object RateLimiter {
  private lazy val scheduler = Executors.newScheduledThreadPool(1, new ThreadFactory {
    override def newThread(r: Runnable): Thread = {
      val t = new Thread(r)
      t.setDaemon(true)
      t
    }
  })

  def delayedFuture[T](delay: FiniteDuration, t: => Future[T]): Future[T] = {
    val promise = Promise[T]
    scheduler.schedule(new Runnable {
      override def run(): Unit = t.onComplete {
        case Success(value) => promise.success(value)
        case Failure(exception) => promise.failure(exception)
      }
    }, delay.length, delay.unit)
    promise.future
  }
}