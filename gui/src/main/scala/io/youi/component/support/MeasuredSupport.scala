package io.youi.component.support

import io.youi.component.Component
import reactify.{Val, Var}

trait MeasuredSupport {
  this: Component =>

  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  object measured {
    lazy val width: Val[Double] = w
    lazy val height: Val[Double] = h
  }

  measure.on {
    val rect = element.getBoundingClientRect()
    w @= rect.width
    h @= rect.height
  }
}