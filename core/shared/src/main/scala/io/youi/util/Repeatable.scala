package io.youi.util

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
  * @see Time.repeat for convenient usage
  */
case class Repeatable(delay: FiniteDuration,
                      initialDelay: Option[FiniteDuration],
                      stopOnError: Boolean,
                      task: () => Future[Unit],
                      errorHandler: Throwable => Unit)
                     (implicit ec: ExecutionContext) {
  private var running = false
  private var keepAlive = true
  private var future: Future[Unit] = Future.successful(())

  def start(): Unit = synchronized {
    if (!running) {
      running = true
      keepAlive = true
      run(initialDelay.getOrElse(delay))
    }
  }

  private def run(delay: FiniteDuration): Unit = synchronized {
    if (keepAlive) {
      future = Time.delay(delay).flatMap { _ =>
        if (keepAlive) {
          task()
        } else {
          Future.successful(())
        }
      }
      future.onComplete {
        case Success(_) => reSchedule()
        case Failure(exception) => {
          errorHandler(exception)
          if (!stopOnError) {
            reSchedule()
          }
        }
      }
    }
  }

  private def reSchedule(): Unit = run(delay)

  def stop(): Unit = synchronized {
    keepAlive = false
  }
}
