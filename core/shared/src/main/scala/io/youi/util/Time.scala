package io.youi.util

import java.util.concurrent.TimeoutException

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

/**
  * Cross-platform functionality for dealing with time
  */
object Time {
  /**
    * Creates a non-blocking Future that is completed after `duration` is elapsed
    *
    * @param duration an amount of time that must elapse before the future is complete
    * @return Future[Unit] that will be complete after `duration`
    */
  def delay(duration: FiniteDuration): Future[Unit] = io.youi.YouIPlatform.delay(duration.toMillis)

  /**
    * Waits for a condition to be true before returning.
    *
    * @param condition the condition function that is being waited for true. Should return false until ready.
    * @param frequency the frequency to check the condition (defaults to 100 milliseconds)
    * @param timeout the timeout to delay before a TimeoutException is thrown (defaults to 5 minutes)
    * @param started the timestamp of when this was started (defaults to current time)
    * @param ec the execution context to use
    * @return Future[Unit]
    */
  def waitFor(condition: => Boolean,
              frequency: FiniteDuration = 100.milliseconds,
              timeout: FiniteDuration = 5.minutes,
              started: Long = System.currentTimeMillis())
             (implicit ec: ExecutionContext): Future[Unit] = {
    val elapsed = System.currentTimeMillis() - started
    if (condition) {
      Future.successful(())
    } else if (elapsed > timeout.toMillis) {
      throw new TimeoutException("Timed out waiting for condition!")
    } else {
      delay(frequency).flatMap { _ =>
        waitFor(condition, frequency, timeout, started)
      }
    }
  }

  def repeat(delay: FiniteDuration,
             initialDelay: Option[FiniteDuration] = None,
             stopOnError: Boolean = true,
             errorHandler: Throwable => Unit = (t: Throwable) => scribe.error("Error during repeat task", t),
             autoStart: Boolean = true)
            (task: => Future[Unit])
            (implicit ec: ExecutionContext): Repeatable = {
    val r = Repeatable(delay, initialDelay, stopOnError, () => task, errorHandler)
    if (autoStart) {
      r.start()
    }
    r
  }
}