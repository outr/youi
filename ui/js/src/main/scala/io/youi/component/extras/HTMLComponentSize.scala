package io.youi.component.extras

import io.youi.component.Component
import io.youi.theme.StringifyImplicits
import org.scalajs.dom.html
import reactify.{Val, Var}

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

  object view {
    lazy val width: Val[Double] = Component.prop(component, e.clientWidth)
    lazy val height: Val[Double] = Component.prop(component, e.clientHeight)
  }

  object scroll {
    lazy val width: Val[Double] = Component.prop(component, e.getBoundingClientRect().width)
    lazy val height: Val[Double] = Component.prop(component, e.getBoundingClientRect().height)
  }
}