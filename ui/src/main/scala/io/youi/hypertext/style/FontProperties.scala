package io.youi.hypertext.style

import reactify.Var
import io.youi.hypertext.Component

class FontProperties(component: Component) {
  val family: Var[String] = component.prop(component.element.style.fontFamily, component.element.style.fontFamily = _, mayCauseResize = true)
  val style: Var[String] = component.prop(component.element.style.fontStyle, component.element.style.fontStyle = _, mayCauseResize = true)
  val size: Var[Double] = component.prop(12.0, d => component.element.style.fontSize = s"${d}px", mayCauseResize = true)
}
