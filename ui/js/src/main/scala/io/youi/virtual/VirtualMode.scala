package io.youi.virtual

sealed trait VirtualMode

object VirtualMode {
  case object Bars extends VirtualMode
  case object Clip extends VirtualMode
  case object Stretch extends VirtualMode
  case object FitWidth extends VirtualMode
  case object FitHeight extends VirtualMode
}