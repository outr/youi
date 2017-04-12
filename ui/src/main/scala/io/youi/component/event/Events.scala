package io.youi.component.event

import com.outr.pixijs.PIXI
import io.youi.component.Component
import reactify.Channel

class Events(component: Component) {
  lazy val click: Channel[MouseEvent] = mouseEvent("click")

  protected def mouseEvent(name: String): Channel[MouseEvent] = {
    val channel = Channel[MouseEvent]
    component.instance.on(name, (evt: PIXI.interaction.InteractionEvent) => {
      channel := new MouseEvent(component, evt)
    })
    channel
  }
}
