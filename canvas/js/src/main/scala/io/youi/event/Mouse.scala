package io.youi.event

import org.scalajs.dom.raw
import reactify.{Channel, Val, Var}

class Mouse {
  val x: Val[Double] = Var(0.0)
  val y: Val[Double] = Var(0.0)
  val wheel: Channel[WheelDelta] = Channel[WheelDelta]
}

case class WheelDelta(x: Double, y: Double, z: Double, mode: DeltaMode, htmlEvent: raw.WheelEvent) {
  override def toString: String = s"WheelData(x: $x, y: $y, z: $z, mode: $mode)"
}

sealed trait DeltaMode

object DeltaMode {
  case object Pixel extends DeltaMode
  case object Line extends DeltaMode
  case object Page extends DeltaMode
}