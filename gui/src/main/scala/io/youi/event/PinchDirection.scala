package io.youi.event

sealed trait PinchDirection

object PinchDirection {
  case object In extends PinchDirection
  case object Out extends PinchDirection
}