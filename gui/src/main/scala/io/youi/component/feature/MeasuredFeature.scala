package io.youi.component.feature

import io.youi.component.Component
import reactify.{Val, Var}

class MeasuredFeature(override val component: Component) extends Feature with WidthFeature with HeightFeature {
  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  override lazy val width: Val[Double] = w
  override lazy val height: Val[Double] = h

  component.measure.on {
    val rect = component.element.getBoundingClientRect()
    w @= rect.width
    h @= rect.height
  }
}