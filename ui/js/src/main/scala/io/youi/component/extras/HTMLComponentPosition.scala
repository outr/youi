package io.youi.component.extras

import io.youi.component.Component
import io.youi.style.Position
import io.youi.theme.{StringifyImplicits, StyleConnect, StyleProp}
import org.scalajs.dom._

class HTMLComponentPosition(override protected val component: Component) extends ComponentPosition with StringifyImplicits {
  private def e: html.Element = HTMLComponent.element(component)

  x.attach { value =>
    e.style.left = s"${value}px"
  }
  y.attach { value =>
    e.style.top = s"${value}px"
  }
  z.attach { value =>
    e.style.zIndex = value.toString
  }

  val `type`: StyleProp[Position] = component.style[Position]("position", if (x() == 0.0 && y() == 0.0) {
    Position.Static
  } else {
    Position.Absolute
  }, StyleConnect.style[Position], ignoreParent = true)
}