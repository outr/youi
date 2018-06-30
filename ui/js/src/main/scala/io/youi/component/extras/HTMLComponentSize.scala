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

  private def prop(get: => Double): Val[Double] = {
    val v = Var(get)
    var updating = false
    component.delta.on {
      updating = true
      try {
        v := get
      } finally {
        updating = false
      }
    }
    v
  }

  object view {
    lazy val width: Val[Double] = prop(e.clientWidth)
    lazy val height: Val[Double] = prop(e.clientHeight)
  }

  object scroll {
    lazy val width: Val[Double] = prop(e.getBoundingClientRect().width)
    lazy val height: Val[Double] = prop(e.getBoundingClientRect().height)
  }
}