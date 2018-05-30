package io.youi.component.extras

import io.youi.component.Component
import io.youi.theme.StringifyImplicits
import org.scalajs.dom.html

class HTMLComponentSize(override protected val component: Component) extends ComponentSize with StringifyImplicits {
  private def e: html.Element = HTMLComponent.element(component)

  width.attach { value =>
    if (value != measured.width()) {
      e.style.width = s"${value.ceil}px"
    } else {
      e.style.removeProperty("width")
    }
  }
  height.attach { value =>
    if (value != measured.height()) {
      e.style.height = s"${value.ceil}px"
    } else {
      e.style.removeProperty("height")
    }
  }
}