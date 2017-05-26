package io.youi.component.event

import com.outr.pixijs.PIXI
import io.youi.component.Component
import reactify.Channel

class Events(component: Component) {
  lazy val click: Channel[MouseEvent] = mouseEvent("click")
  lazy val tap: Channel[MouseEvent] = mouseEvent("tap")
  object mouse {
    lazy val down: Channel[MouseEvent] = mouseEvent("mousedown")
    lazy val move: Channel[MouseEvent] = mouseEvent("mousemove")
    lazy val out: Channel[MouseEvent] = mouseEvent("mouseout")
    lazy val over: Channel[MouseEvent] = mouseEvent("mouseover")
    lazy val up: Channel[MouseEvent] = mouseEvent("mouseup")
    lazy val upOutside: Channel[MouseEvent] = mouseEvent("mouseupoutside")
  }
  object pointer {
    lazy val cancel: Channel[MouseEvent] = mouseEvent("pointercancel")
    lazy val down: Channel[MouseEvent] = mouseEvent("pointerdown")
    lazy val move: Channel[MouseEvent] = mouseEvent("pointermove")
    lazy val out: Channel[MouseEvent] = mouseEvent("pointerout")
    lazy val over: Channel[MouseEvent] = mouseEvent("pointerover")
    lazy val tap: Channel[MouseEvent] = mouseEvent("pointertap")
    lazy val up: Channel[MouseEvent] = mouseEvent("pointerup")
    lazy val upOutside: Channel[MouseEvent] = mouseEvent("pointerupoutside")
  }
  object touch {
    lazy val cancel: Channel[MouseEvent] = mouseEvent("touchcancel")
    lazy val end: Channel[MouseEvent] = mouseEvent("touchend")
    lazy val endOutside: Channel[MouseEvent] = mouseEvent("touchendOutside")
    lazy val move: Channel[MouseEvent] = mouseEvent("touchmove")
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