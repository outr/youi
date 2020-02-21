package io.youi.ui.support

import io.youi.ui.Component
import io.youi.ui.types.Prop

import org.scalajs.dom._

trait ContainerSupport {
  this: Component =>

  lazy val title: Prop[String] = new Prop(document.title, document.title_=)

  object children {
    def length: Int = element.childElementCount
    def +=(component: Component): Unit = element.appendChild(component.element)
    def -=(component: Component): Unit = element.removeChild(component.element)
  }
}
