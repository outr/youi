package io.youi.hypertext.style

import reactify.Var
import io.youi.Size
import io.youi.hypertext.Component

class FontProperties(component: Component) {
  val family: Var[String] = component.prop(component.element.style.fontFamily, component.element.style.fontFamily = _, mayCauseResize = true)
  val style: Var[String] = component.prop(component.element.style.fontStyle, component.element.style.fontStyle = _, mayCauseResize = true)
  val size: Var[Size] = component.prop(Size.Auto, s => component.element.style.fontSize = s.toString, mayCauseResize = true)
}
