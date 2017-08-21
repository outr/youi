package io.youi.event

import reactify.{Channel, InvocationType}
import PointerEvent.Type
import io.youi.component.Component

class Events(component: Component) {
  lazy val click: Channel[PointerEvent] = pointer.byType(Type.Click)
  lazy val doubleClick: Channel[PointerEvent] = pointer.byType(Type.DoubleClick)

  object pointer extends Channel[PointerEvent] {
    lazy val move: Channel[PointerEvent] = byType(Type.Move)
    lazy val enter: Channel[PointerEvent] = byType(Type.Enter)
    lazy val exit: Channel[PointerEvent] = byType(Type.Exit)
    lazy val down: Channel[PointerEvent] = byType(Type.Down)
    lazy val up: Channel[PointerEvent] = byType(Type.Up)
    lazy val cancel: Channel[PointerEvent] = byType(Type.Cancel)
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

  lazy val gestures: Gestures = new Gestures(component)
}