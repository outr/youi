package io.youi.component.extras

import io.youi.component.Component
import io.youi.style.Position
import org.scalajs.dom.html
import reactify.Var

class HTMLComponentPosition(component: Component) extends ComponentPosition(component) {
  private def e: html.Element = HTMLComponent.element(component)

  lazy val `type`: Var[Position] = {
    component.connect[Position](
      v = Var(Position.Static),
      set = (p: Position) => e.style.position = p.toString.toLowerCase
    )
  }

  component.connect(x, (v: Double) => e.style.left = s"${v}px")
  component.connect(y, (v: Double) => e.style.top = s"${v}px")
}
