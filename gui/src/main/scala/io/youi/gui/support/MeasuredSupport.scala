package io.youi.gui.support

import io.youi.gui.Component
import io.youi.gui.util.Measurer
import reactify.{Val, Var}

trait MeasuredSupport {
  this: Component =>

  protected val x: Var[Double] = Var(0.0)
  protected val y: Var[Double] = Var(0.0)
  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  object measured {
    lazy val x: Val[Double] = x
    lazy val y: Val[Double] = y
    lazy val width: Val[Double] = w
    lazy val height: Val[Double] = h
  }

  override protected def measure(): Unit = {
    Measurer.measureHTML(element.outerHTML, element.style.width, element.style.height, x, y, w, h)
  }
}
