package io.youi.gui.event

import io.youi.gui.Component
import reactify.{Channel, Val, Var}
import org.scalajs.{dom => jsdom}

class Events(component: Component) {
  lazy val click: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Click)
  lazy val doubleClick: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.DoubleClick)
  lazy val contextMenu: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.ContextMenu)

  object pointer {
    lazy val move: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Move)
    lazy val enter: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Enter)
    lazy val exit: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Exit)
    lazy val down: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Down)
    lazy val up: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Up)
    lazy val cancel: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Cancel)
    lazy val wheel: Channel[WheelEvent] = wheelChannel()

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
  }

  private def pointerChannel(`type`: PointerEvent.Type): Channel[PointerEvent] = {
    val channel = Channel[PointerEvent]
    component.element.addEventListener(`type`.htmlTypeString, (evt: jsdom.MouseEvent) => {
      val pe = new PointerEvent(evt, `type`)
      channel @= pe
    })
    channel
  }

  private def wheelChannel(): Channel[WheelEvent] = {
    val channel = Channel[WheelEvent]
    component.element.addEventListener(PointerEvent.Type.Wheel.htmlTypeString, (evt: jsdom.WheelEvent) => {
      val we = new WheelEvent(evt)
      channel @= we
    })
    channel
  }
}
