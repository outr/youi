package io.youi.component.support

import io.youi.component.Component

trait ContainerSupport {
  this: Component =>

  object children {
    def length: Int = element.childElementCount
    def +=(component: Component): Unit = {
      element.appendChild(component.element)
      measure()
    }
    def -=(component: Component): Unit = {
      element.removeChild(component.element)
      measure()
    }
    def ++=(seq: Seq[Component]): Unit = seq.foreach(+=)
    def --=(seq: Seq[Component]): Unit = seq.foreach(-=)
  }
}
