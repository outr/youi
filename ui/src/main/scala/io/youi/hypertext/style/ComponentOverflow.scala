package io.youi.hypertext.style

import io.youi.hypertext.Component
import reactify.Var

class ComponentOverflow(component: Component) {
  val x: Var[Overflow] = component.prop(Overflow.Unset, o => component.element.style.overflowX = o.value, mayCauseResize = true)
  val y: Var[Overflow] = component.prop(Overflow.Unset, o => component.element.style.overflowY = o.value, mayCauseResize = true)

  def :=(value: Overflow): Unit = set(value)
  def set(value: Overflow): Unit = {
    x := value
    y := value
  }
}
