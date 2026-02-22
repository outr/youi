package io.youi.util

import rapid.Task

import java.util.concurrent.TimeoutException
import scala.concurrent.duration._

/**
  * Cross-platform functionality for dealing with time
  */
object Time {
  /**
    * Waits for a condition to be true before returning.
    *
    * @param condition the condition function that is being waited for true. Should return false until ready.
    * @param frequency the frequency to check the condition (defaults to 100 milliseconds)
    * @param timeout the timeout to delay before a TimeoutException is thrown (defaults to 5 minutes)
    * @param started the timestamp of when this was started (defaults to current time)
    * @return Task[Unit]
    */
  def waitFor(condition: => Boolean,
              frequency: FiniteDuration = 100.milliseconds,
              timeout: FiniteDuration = 5.minutes,
              started: Long = System.currentTimeMillis()): Task[Unit] = {
    val elapsed = System.currentTimeMillis() - started
    if (condition) {
      Task.unit
    } else if (elapsed > timeout.toMillis) {
      throw new TimeoutException("Timed out waiting for condition!")
    } else {
      Task.sleep(frequency).flatMap { _ =>
        waitFor(condition, frequency, timeout, started)
      }
    }
  }

  def repeat(delay: FiniteDuration,
             initialDelay: Option[FiniteDuration] = None,
             stopOnError: Boolean = true,
             errorHandler: Throwable => Unit = (t: Throwable) => scribe.error("Error during repeat task", t),
             autoStart: Boolean = true)
            (task: => Task[Unit]): Repeatable = {
    val r = Repeatable(delay, initialDelay, stopOnError, () => task, errorHandler)
    if (autoStart) {
      r.start()
    }
    r
  }
}
