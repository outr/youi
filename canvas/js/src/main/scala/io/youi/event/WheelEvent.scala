package io.youi.event

import io.youi.spatial.Point

class WheelEvent(local: Point,
                 global: Point,
                 val delta: WheelDelta,
                 override val htmlEvent: org.scalajs.dom.raw.WheelEvent)
  extends PointerEvent(PointerEvent.Type.Wheel, local, global, htmlEvent) {
  override def toString: String = s"WheelEvent(local: $local, global: $global, delta: $delta)"
}

object WheelEvent {
  def apply(local: Point, global: Point, delta: WheelDelta): WheelEvent = {
    new WheelEvent(local, global, delta, delta.htmlEvent)
  }
}