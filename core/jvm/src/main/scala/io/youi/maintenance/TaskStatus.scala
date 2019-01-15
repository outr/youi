package io.youi.maintenance

sealed trait TaskStatus

object TaskStatus {
  case object Repeat extends TaskStatus
  case object RepeatNow extends TaskStatus
  case object Stop extends TaskStatus
}