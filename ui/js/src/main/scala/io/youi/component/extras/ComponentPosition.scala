package io.youi.component.extras

import io.youi.component.Component
import io.youi.theme.StringifyImplicits
import reactify._

trait ComponentPosition extends StringifyImplicits {
  protected def component: Component

  def x: Var[Double]
  def y: Var[Double]
  def z: Var[Int]

  lazy val left: Var[Double] = x
  lazy val center: Dep[Double, Double] = Dep(left, component.size.width / 2.0)
  lazy val right: Dep[Double, Double] = Dep(left, component.size.width())

  lazy val top: Var[Double] = y
  lazy val middle: Dep[Double, Double] = Dep(top, component.size.height / 2.0)
  lazy val bottom: Dep[Double, Double] = Dep(top, component.size.height())

  lazy val depth: Var[Int] = z
}
