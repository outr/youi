package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.util.Measurer
import reactify.{Val, Var}

class PreferredSizeFeature(component: Component) extends Feature(component) {
  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  lazy val width: Val[Double] = w
  lazy val height: Val[Double] = h

  component.measure.on(Measurer.measureHTML(element.outerHTML, element.style.width, element.style.height, w, h))
}