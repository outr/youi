package io.youi.component.extras

import io.youi.component.Component
import io.youi.style.Length
import io.youi.theme.StringifyImplicits
import reactify._

trait ComponentPosition extends StringifyImplicits {
  protected def component: Component

  def x: Var[Length]
  def y: Var[Length]
  def z: Var[Int]

  lazy val left: Var[Length] = x
  lazy val center: Dep[Length, Length] = Dep(left, component.size.width / 2.0)
  lazy val right: Dep[Length, Length] = Dep(left, component.size.width())

  lazy val top: Var[Length] = y
  lazy val middle: Dep[Length, Length] = Dep(top, component.size.height / 2.0)
  lazy val bottom: Dep[Length, Length] = Dep(top, component.size.height())

  lazy val depth: Var[Int] = z
}
