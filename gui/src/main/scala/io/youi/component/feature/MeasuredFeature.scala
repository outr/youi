package io.youi.component.feature

import io.youi.component.Component
import reactify.{Val, Var}

class MeasuredFeature(component: Component) extends Feature(component) {
  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  lazy val width: Val[Double] = w
  lazy val height: Val[Double] = h

  component.measure.on {
    val rect = element.getBoundingClientRect()
    w @= rect.width
    h @= rect.height
  }
}