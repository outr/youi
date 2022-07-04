package io.youi.util

import cats.effect.IO

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.ExecutionContext
import cats.effect.unsafe.implicits.global

/**
  * @see Time.repeat for convenient usage
  */
case class Repeatable(delay: FiniteDuration,
                      initialDelay: Option[FiniteDuration],
                      stopOnError: Boolean,
                      task: () => IO[Unit],
                      errorHandler: Throwable => Unit)
                     (implicit ec: ExecutionContext) {
  private var running = false
  private var keepAlive = true

  def start(): Unit = synchronized {
    if (!running) {
      running = true
      keepAlive = true
      run(initialDelay.getOrElse(delay))
    }
  }

  private def run(delay: FiniteDuration): Unit = synchronized {
    if (keepAlive && Repeatable.keepAlive) {
      IO.sleep(delay).flatMap { _ =>
        if (keepAlive && Repeatable.keepAlive) {
          task()
        } else {
          IO.unit
        }
      }.attempt.map {
        case Left(throwable) =>
          errorHandler(throwable)
          if (!stopOnError) reSchedule()
        case Right(_) => reSchedule()
      }.unsafeRunAndForget()
    }
  }

  private def reSchedule(): Unit = run(delay)

  def stop(): Unit = synchronized {
    keepAlive = false
  }
}

// TODO: Merge Maintenane functionality and TaskStatus for much more powerful implementation
object Repeatable {
  private var keepAlive: Boolean = true

  def dispose(): Unit = keepAlive = false
}
