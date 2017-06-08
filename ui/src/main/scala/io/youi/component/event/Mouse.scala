package io.youi.component.event

import reactify.{Channel, Val, Var}

object Mouse {
  val x: Val[Double] = Var(0.0)
  val y: Val[Double] = Var(0.0)
  val wheel: Channel[WheelDelta] = Channel[WheelDelta]
}

case class WheelDelta(x: Double, y: Double, z: Double, mode: DeltaMode)

sealed trait DeltaMode

object DeltaMode {
  case object Pixel extends DeltaMode
  case object Line extends DeltaMode
  case object Page extends DeltaMode
}