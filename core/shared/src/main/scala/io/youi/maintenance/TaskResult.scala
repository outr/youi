package io.youi.maintenance

import scala.concurrent.duration.FiniteDuration

sealed trait TaskResult

object TaskResult {
  case object Continue extends TaskResult
  case object Stop extends TaskResult
  case object RunAgain extends TaskResult
  case class ChangeSchedule(delay: () => FiniteDuration) extends TaskResult
  object ChangeSchedule {
    def to(delay: => FiniteDuration): ChangeSchedule = new ChangeSchedule(() => delay)
  }
  case class NextSchedule(delay: FiniteDuration) extends TaskResult
}