package io.youi.gui.event

sealed trait PinchDirection

object PinchDirection {
  case object In extends PinchDirection
  case object Out extends PinchDirection
}