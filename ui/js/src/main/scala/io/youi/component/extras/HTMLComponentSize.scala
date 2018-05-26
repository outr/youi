package io.youi.component.extras

import io.youi.component.Component
import io.youi.theme.{StringifyImplicits, StyleConnect}
import reactify.Var

class HTMLComponentSize(override protected val component: Component) extends ComponentSize with StringifyImplicits {
  private lazy val ceilFunction = (d: Double) => math.ceil(d)

  override lazy val width: Var[Double] = component.style[Double]("width", 0.0, StyleConnect.style[Double](ceilFunction))
  override lazy val height: Var[Double] = component.style[Double]("height", 0.0, StyleConnect.style[Double](ceilFunction))
}