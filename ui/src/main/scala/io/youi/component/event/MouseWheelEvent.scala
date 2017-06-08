package io.youi.component.event

import com.outr.pixijs.PIXI
import io.youi.component.Component

class MouseWheelEvent(component: Component,
                      evt: PIXI.interaction.InteractionEvent,
                      val delta: WheelDelta) extends MouseEvent(component, evt)