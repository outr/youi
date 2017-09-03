package io.youi.event

class WheelEvent(x: Double,
                 y: Double,
                 globalX: Double,
                 globalY: Double,
                 val delta: WheelDelta,
                 override val htmlEvent: org.scalajs.dom.raw.WheelEvent)
  extends PointerEvent(PointerEvent.Type.Wheel, x, y, globalX, globalY, htmlEvent, HTMLEventType.Mouse) {
  override def toString: String = s"WheelEvent(local: $local, global: $global, delta: $delta)"
}

object WheelEvent {
  def apply(x: Double, y: Double, globalX: Double, globalY: Double, delta: WheelDelta): WheelEvent = {
    new WheelEvent(x, y, globalX, globalY, delta, delta.htmlEvent)
  }
}