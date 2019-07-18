package io.youi.maintenance

import scala.concurrent.duration.FiniteDuration

sealed trait TaskStatus

object TaskStatus {
  case object Repeat extends TaskStatus
  case object RepeatNow extends TaskStatus
  case class RepeatIn(time: FiniteDuration) extends TaskStatus
  case object Stop extends TaskStatus
}