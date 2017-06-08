package io.youi.component.event

import com.outr.pixijs.PIXI
import io.youi.component.Component
import reactify.{Channel, Val, Var}

class Events(component: Component) {
  lazy val click: Channel[MouseEvent] = mouseEvent("click")
  lazy val tap: Channel[MouseEvent] = mouseEvent("tap")
  object mouse {
    lazy val down: Channel[MouseEvent] = mouseEvent("mousedown")
    lazy val move: Channel[MouseEvent] = mouseEvent("mousemove")
    lazy val (moveInside, moveOutside) = {
      val inside = Channel[MouseEvent]
      val outside = Channel[MouseEvent]

      move.attach { evt =>
        if (evt.x >= 0.0 && evt.y >= 0.0 && evt.x <= component.size.width() && evt.y <= component.size.height()) {
          inside := evt
        } else {
          outside := evt
        }
      }

      (inside, outside)
    }
    lazy val out: Channel[MouseEvent] = mouseEvent("mouseout")
    lazy val over: Channel[MouseEvent] = mouseEvent("mouseover")
    lazy val up: Channel[MouseEvent] = mouseEvent("mouseup")
    lazy val upOutside: Channel[MouseEvent] = mouseEvent("mouseupoutside")
    lazy val wheel: Channel[MouseWheelEvent] = {
      val c = Channel[MouseWheelEvent]
      val lastMove = pointer.lastMove
      Mouse.wheel.attach { delta =>
        if (pointer.overState()) {
          c := new MouseWheelEvent(component, lastMove().get.evt, delta)
        }
      }
      c
    }
  }
  object pointer {
    lazy val cancel: Channel[MouseEvent] = mouseEvent("pointercancel")
    lazy val down: Channel[MouseEvent] = mouseEvent("pointerdown")
    lazy val move: Channel[MouseEvent] = mouseEvent("pointermove")
    lazy val (moveInside, moveOutside) = {
      val inside = Channel[MouseEvent]
      val outside = Channel[MouseEvent]

      move.attach { evt =>
        if (evt.x >= 0.0 && evt.y >= 0.0 && evt.x <= component.size.width() && evt.y <= component.size.height()) {
          inside := evt
        } else {
          outside := evt
        }
      }

      (inside, outside)
    }
    lazy val out: Channel[MouseEvent] = mouseEvent("pointerout")
    lazy val over: Channel[MouseEvent] = mouseEvent("pointerover")
    lazy val tap: Channel[MouseEvent] = mouseEvent("pointertap")
    lazy val up: Channel[MouseEvent] = mouseEvent("pointerup")
    lazy val upOutside: Channel[MouseEvent] = mouseEvent("pointerupoutside")

    lazy val (x: Val[Double], y: Val[Double], lastMove: Val[Option[MouseEvent]]) = {
      val px = Var[Double](0.0)
      val py = Var[Double](0.0)
      val m = Var[Option[MouseEvent]](None)
      pointer.moveInside.attach { evt =>
        px := evt.x
        py := evt.y
        m := Some(evt)
      }
      (px, py, m)
    }

    /**
      * True if the pointer is currently within the boundaries of this Component.
      */
    lazy val overState: Val[Boolean] = {
      val v = Var(false)
      pointer.over.on(v := true)
      pointer.out.on(v := false)
      pointer.moveInside.on(v := true)
      v
    }
  }
  object touch {
    lazy val cancel: Channel[MouseEvent] = mouseEvent("touchcancel")
    lazy val end: Channel[MouseEvent] = mouseEvent("touchend")
    lazy val endOutside: Channel[MouseEvent] = mouseEvent("touchendOutside")
    lazy val move: Channel[MouseEvent] = mouseEvent("touchmove")
    lazy val (moveInside, moveOutside) = {
      val inside = Channel[MouseEvent]
      val outside = Channel[MouseEvent]

      move.attach { evt =>
        if (evt.x >= 0.0 && evt.y >= 0.0 && evt.x <= component.size.width() && evt.y <= component.size.height()) {
          inside := evt
        } else {
          outside := evt
        }
      }

      (inside, outside)
    }
    lazy val start: Channel[MouseEvent] = mouseEvent("touchstart")
  }

  protected def mouseEvent(name: String): Channel[MouseEvent] = {
    val channel = Channel[MouseEvent]
    component.instance.on(name, (evt: PIXI.interaction.InteractionEvent) => {
      channel := new MouseEvent(component, evt)
    })
    channel
  }
}