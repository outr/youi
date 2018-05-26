package io.youi.component.extras

import io.youi.Length
import io.youi.component.Component
import io.youi.style.Position
import io.youi.theme.{StringifyImplicits, StyleConnect}
import reactify.Var

class HTMLComponentPosition(override protected val component: Component) extends ComponentPosition with StringifyImplicits {
  override val x: Var[Length] = component.style[Length]("left", Length.default, StyleConnect.style[Length])
  override val y: Var[Length] = component.style[Length]("top", Length.default, StyleConnect.style[Length])
  override val z: Var[Int] = component.style[Int]("zIndex", 0, StyleConnect.style[Int])

  val `type`: Var[Position] = component.style[Position]("position", if (x.isDefault && y.isDefault) {
    Position.Static
  } else {
    Position.Absolute
  }, StyleConnect.style[Position], ignoreParent = true)
}