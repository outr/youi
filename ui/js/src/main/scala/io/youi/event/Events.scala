package io.youi.event

import io.youi.component.Component
import reactify._

class Events(component: Component) {
  import PointerEvent.Type

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

    lazy val (x: Val[Double], y: Val[Double], overState: Val[Boolean]) = {
      val px = Var[Double](0.0)
      val py = Var[Double](0.0)
      val o = Var[Boolean](false)
      pointer.enter.on(o @= true)
      pointer.exit.on(o @= false)
      pointer.move.attach { evt =>
        px @= evt.local.x
        py @= evt.local.y
      }
      (px, py, o)
    }

    lazy val downState: Val[Boolean] = {
      val d = Var[Boolean](false)
      pointer.down.on(d @= true)
      pointer.up.on(d @= false)
      pointer.exit.on(d @= false)
      d
    }

    def byType(types: PointerEvent.Type*): Channel[PointerEvent] = {
      val set = types.toSet
      collect {
        case evt if set.contains(evt.`type`) => evt
      }
    }

    override def set(value: => PointerEvent): Unit = fire(value, None)
  }
  object touch extends Channel[PointerEvent] {
    lazy val start: Channel[PointerEvent] = byType(Type.TouchStart)
    lazy val move: Channel[PointerEvent] = byType(Type.TouchMove)
    lazy val cancel: Channel[PointerEvent] = byType(Type.TouchCancel)
    lazy val end: Channel[PointerEvent] = byType(Type.TouchEnd)

    pointer.collect {
      case evt if evt.isTouch => set(evt)
    }

    def byType(types: PointerEvent.Type*): Channel[PointerEvent] = {
      val set = types.toSet
      collect {
        case evt if set.contains(evt.`type`) => evt
      }
    }

    override def set(value: => PointerEvent): Unit = fire(value, None)
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

    override def set(value: => KeyEvent): Unit = fire(value, None)
  }

//  lazy val gestures: Gestures = new Gestures(component)
}