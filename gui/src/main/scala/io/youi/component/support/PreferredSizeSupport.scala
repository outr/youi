package io.youi.component.support

import io.youi.component.Component
import io.youi.component.util.Measurer
import reactify.{Val, Var}

trait PreferredSizeSupport {
  this: Component =>

  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  object preferred {
    lazy val width: Val[Double] = w
    lazy val height: Val[Double] = h
  }

  measure.on(Measurer.measureHTML(element.outerHTML, element.style.width, element.style.height, w, h))
}