package io.youi.component.extras

import io.youi.component.Component
import io.youi.style.Length
import io.youi.theme.{StringifyImplicits, StyleConnect}
import reactify.Var

class HTMLComponentSize(override protected val component: Component) extends ComponentSize with StringifyImplicits {
  override lazy val width: Var[Length] = component.style[Length]("width", Length.default, StyleConnect.style[Length])
  override lazy val height: Var[Length] = component.style[Length]("height", Length.default, StyleConnect.style[Length])
}