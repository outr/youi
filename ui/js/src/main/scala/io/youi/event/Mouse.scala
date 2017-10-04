package io.youi.event

import io.youi.ui
import org.scalajs.dom.raw
import reactify.{Channel, Val, Var}

object Mouse {
  private val _x = Var(0.0)
  private val _y = Var(0.0)

  def x: Val[Double] = _x
  def y: Val[Double] = _y
  val wheel: Channel[WheelDelta] = Channel[WheelDelta]

  ui.event.pointer.move.attach { evt =>
    _x := evt.pageX
    _y := evt.pageY
  }
  ui.event.pointer.wheel.attach { evt =>
    val mode: DeltaMode = evt.deltaMode match {
      case 0x00 => DeltaMode.Pixel
      case 0x01 => DeltaMode.Line
      case 0x02 => DeltaMode.Page
    }
    val d = WheelDelta(evt.deltaX, evt.deltaY, evt.deltaZ, mode, evt)
    wheel := d
  }
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