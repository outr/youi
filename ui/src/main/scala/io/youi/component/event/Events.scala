package io.youi.component.event

import com.outr.pixijs.PIXI
import io.youi.component.Component
import reactify.{Channel, Val, Var}

class Events(component: Component) {
  lazy val click: Channel[MouseEvent] = mouseEvent("click")
  lazy val tap: Channel[MouseEvent] = mouseEvent("tap")
  object pointer {
    lazy val cancel: Channel[MouseEvent] = mouseEvent("pointercancel")
    lazy val down: Channel[MouseEvent] = mouseEvent("pointerdown")
    lazy val move: Channel[MouseEvent] = mouseEvent("pointermove")
    lazy val out: Channel[MouseEvent] = mouseEvent("pointerout")
    lazy val over: Channel[MouseEvent] = mouseEvent("pointerover")
    lazy val tap: Channel[MouseEvent] = mouseEvent("pointertap")
    lazy val up: Channel[MouseEvent] = mouseEvent("pointerup")
    lazy val upOutside: Channel[MouseEvent] = mouseEvent("pointerupoutside")

    lazy val (x: Val[Double], y: Val[Double], overState: Val[Boolean]) = {
      val px = Var[Double](0.0)
      val py = Var[Double](0.0)
      val o = Var[Boolean](false)
      pointer.over.on(o := true)
      pointer.out.on(o := false)
      pointer.move.attach { evt =>
        px := evt.x
        py := evt.y
        o := evt.inside
      }
      (px, py, o)
    }
    lazy val pinch: PinchSupport = new PinchSupport(component)
    lazy val wheel: Channel[MouseWheelEvent] = {
      val c = Channel[MouseWheelEvent]
      Mouse.wheel.attach { delta =>
        if (overState()) {
          c := new MouseWheelEvent(component, x(), y(), Mouse.x(), Mouse.y(), delta)
        }
      }
      c
    }
  }

  protected def mouseEvent(name: String): Channel[MouseEvent] = {
    val channel = Channel[MouseEvent]
    component.instance.on(name, (evt: PIXI.interaction.InteractionEvent) => {
      channel := new MouseEvent(component, evt)
    })
    channel
  }
}