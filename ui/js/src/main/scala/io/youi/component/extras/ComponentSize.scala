package io.youi.component.extras

import io.youi.component.Component
import reactify.{Val, Var}

class ComponentSize(component: Component) {
  object measured {
//    val width: Var[Double] = component.prop(0.0, updatesRendering = true)
//    val height: Var[Double] = component.prop(0.0, updatesRendering = true)
    val width: Var[Double] = Var(0.0)
    val height: Var[Double] = Var(0.0)
  }

  def reset(width: Boolean = true, height: Boolean = true): Unit = {
    if (width) this.width.set(measured.width())
    if (height) this.height.set(measured.height())
  }

//  val width: Var[Double] = component.prop(measured.width, updatesRendering = true)
//  val height: Var[Double] = component.prop(measured.height, updatesRendering = true)
  val width: Var[Double] = Var(0.0)
  val height: Var[Double] = Var(0.0)

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)
}
