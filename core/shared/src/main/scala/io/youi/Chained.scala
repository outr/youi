package io.youi

import cats.effect.IO

import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.duration._

case class Chained(concurrency: Int = 1, delay: FiniteDuration = 100.millis) {
  private val active = new AtomicBoolean(false)

  private def lock(): IO[Unit] = IO(active.compareAndSet(false, true)).flatMap {
    case true => IO.unit
    case false => IO.sleep(delay).flatMap(_ => lock())
  }

  private def unlock(): IO[Unit] = IO(active.set(false))

  def apply[Return](effect: IO[Return]): IO[Return] = lock().flatMap { _ =>
    effect.guarantee(unlock())
  }
}