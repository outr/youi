package io.youi.util

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

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
}