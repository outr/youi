package io.youi.ui.event

import org.scalajs.{dom => jsdom}

class WheelEvent(underlying: jsdom.WheelEvent) extends PointerEvent(underlying, PointerEvent.Type.Wheel) {
  val mode: DeltaMode = underlying.deltaMode match {
    case 0 => DeltaMode.Pixel
    case 1 => DeltaMode.Line
    case 2 => DeltaMode.Page
  }
  val delta: WheelDelta = WheelDelta(underlying.deltaX, underlying.deltaY, underlying.deltaZ, mode)
}
