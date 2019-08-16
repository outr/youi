package io.youi.event

import io.youi.component.Component
import io.youi.component.extras.HTMLComponent
import org.scalajs.dom.raw

abstract class HTMLEvent(val target: Component,
                         val htmlEvent: raw.UIEvent) extends Event {
  lazy val actualTarget: Option[Component] = HTMLComponent.find(target, htmlEvent.target)

  override def stopPropagation(): Unit = {
    super.stopPropagation()

    htmlEvent.stopPropagation()
  }

  def stopImmediatePropagation(): Unit = {
    stopPropagation()

    htmlEvent.stopImmediatePropagation()
  }

  def preventDefault(): Unit = htmlEvent.preventDefault()
}
