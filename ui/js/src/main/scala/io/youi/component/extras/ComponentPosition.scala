package io.youi.component.extras

import io.youi.component.Component
import reactify.{Dep, Var}

class ComponentPosition(component: Component) {
  lazy val x: Var[Double] = component.prop(0.0, updatesTransform = true)
  lazy val y: Var[Double] = component.prop(0.0, updatesTransform = true)
  lazy val z: Var[Int] = component.prop(0, updatesTransform = true)

  lazy val left: Var[Double] = x
  lazy val center: Dep[Double, Double] = Dep(left, component.size.width / 2.0)
  lazy val right: Dep[Double, Double] = Dep(left, component.size.width)

  lazy val top: Var[Double] = y
  lazy val middle: Dep[Double, Double] = Dep(top, component.size.height / 2.0)
  lazy val bottom: Dep[Double, Double] = Dep(top, component.size.height)

  lazy val depth: Var[Int] = z
}
