package io.youi.client.intercept

import cats.effect.IO
import io.youi.http.HttpRequest

import scala.concurrent.duration._

case class RateLimiter(perRequestDelay: FiniteDuration) extends InterceptorAdapter { self =>
  private val maxDelay = perRequestDelay.toMillis
  @volatile private var lastTime: Long = 0L

  override def before(request: HttpRequest): IO[HttpRequest] = IO.unit.flatMap { _ =>
    self.synchronized {
      val now = System.currentTimeMillis()
      val delay = lastTime + maxDelay
      if (delay > 0L) {
        lastTime = now
        IO.sleep(delay.millis).map(_ => request)
      } else {
        IO.pure(request)
      }
    }
  }
}