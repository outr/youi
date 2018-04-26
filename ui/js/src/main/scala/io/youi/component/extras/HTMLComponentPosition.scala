package io.youi.component.extras

import io.youi.component.Component
import io.youi.style.Position
import io.youi.ui
import org.scalajs.dom.html
import reactify.Var

class HTMLComponentPosition(component: Component) extends ComponentPosition(component) {
  private def e: html.Element = HTMLComponent.element(component)

  val `type`: Var[Position] = Var(Position.Absolute)

  if (component != ui) {
    component.connect[Position](
      v = Var(Position.Absolute),
      set = (p: Position) => e.style.position = p.toString.toLowerCase
    )
    component.connect(x, (v: Double) => e.style.left = s"${v}px")
    component.connect(y, (v: Double) => e.style.top = s"${v}px")
  }
}
