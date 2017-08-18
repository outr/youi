package io.youi.event

import org.scalajs.dom.raw.MouseEvent

class PointerEvent(val `type`: PointerEvent.Type,
                   val htmlEvent: MouseEvent) extends Event

object PointerEvent {
  sealed trait Type

  object Type {
    case object Click extends Type
    case object DoubleClick extends Type
    case object Move extends Type
    case object Enter extends Type
    case object Exit extends Type
    case object Down extends Type
    case object Up extends Type
    case object Wheel extends Type
  }
}

class WheelEvent(val delta: Double, override val htmlEvent: org.scalajs.dom.raw.WheelEvent)
  extends PointerEvent(PointerEvent.Type.Wheel, htmlEvent)