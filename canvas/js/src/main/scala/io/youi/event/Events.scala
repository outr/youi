package io.youi.event

import reactify.{Channel, InvocationType}
import PointerEvent.Type._

class Events {
  lazy val click: Channel[PointerEvent] = pointer.byType(Click)
  lazy val doubleClick: Channel[PointerEvent] = pointer.byType(DoubleClick)

  object pointer extends Channel[PointerEvent] {
    lazy val move: Channel[PointerEvent] = byType(Move)
    lazy val enter: Channel[PointerEvent] = byType(Enter)
    lazy val exit: Channel[PointerEvent] = byType(Exit)
    lazy val down: Channel[PointerEvent] = byType(Down)
    lazy val up: Channel[PointerEvent] = byType(Up)
    lazy val wheel: Channel[WheelEvent] = collect {
      case evt: WheelEvent => evt
    }

    def byType(types: PointerEvent.Type*): Channel[PointerEvent] = {
      val set = types.toSet
      collect {
        case evt if set.contains(evt.`type`) => evt
      }
    }

    override def set(value: => PointerEvent): Unit = fire(value, InvocationType.Direct)
  }

  object key extends Channel[KeyEvent] {
    import KeyEvent.Type._

    lazy val down: Channel[KeyEvent] = byType(Down)
    lazy val press: Channel[KeyEvent] = byType(Press)
    lazy val up: Channel[KeyEvent] = byType(Up)

    def byType(types: KeyEvent.Type*): Channel[KeyEvent] = {
      val set = types.toSet
      collect {
        case evt if set.contains(evt.`type`) => evt
      }
    }

    override def set(value: => KeyEvent): Unit = fire(value, InvocationType.Direct)
  }
}