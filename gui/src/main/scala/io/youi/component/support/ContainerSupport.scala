package io.youi.component.support

import io.youi.component.Component
import org.scalajs.dom.html

trait ContainerSupport {
  this: Component =>

  object children {
    def length: Int = element.childElementCount
    def +=(component: Component): Unit = +=(component.element)
    def +=(child: html.Element): Unit = {
      element.appendChild(child)
      measure()
    }
    def -=(component: Component): Unit = -=(component.element)
    def -=(child: html.Element): Unit = {
      element.removeChild(child)
      measure()
    }
    def ++=(seq: Seq[Component]): Unit = seq.foreach(+=)
    def --=(seq: Seq[Component]): Unit = seq.foreach(-=)
  }
}