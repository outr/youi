package io.youi.component.extras

import io.youi.component.Component
import io.youi.style.Position
import io.youi.theme.{StringifyImplicits, StyleConnect}
import reactify.Var

class HTMLComponentPosition(override protected val component: Component) extends ComponentPosition with StringifyImplicits {
  override val x: Var[Double] = component.style[Double]("left", 0.0, StyleConnect.style[Double])
  override val y: Var[Double] = component.style[Double]("top", 0.0, StyleConnect.style[Double])
  override val z: Var[Int] = component.style[Int]("z-index", 0, StyleConnect.style[Int])

  val `type`: Var[Position] = component.style[Position]("position", if (x() == 0.0 && y() == 0.0) {
    Position.Static
  } else {
    Position.Absolute
  }, StyleConnect.style[Position], ignoreParent = true)
}