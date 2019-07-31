package io.youi.component.extras

import io.youi.component.Component
import io.youi.spatial.{Dim, DimType, Size}
import reactify.{Val, Var}

import scala.language.implicitConversions

trait ComponentSize {
  protected def component: Component

  def apply(): Size = Size(width(), height())
  def :=(size: => Size): Unit = {
    width := size.width
    height := size.height
  }

  object measured {
    lazy val width: Var[Double] = Var(0.0)
    lazy val height: Var[Double] = Var(0.0)
  }

  lazy val width: NumberVar = new NumberVar
  lazy val height: NumberVar = new NumberVar

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)

  reset()

  def reset(width: Boolean = true, height: Boolean = true): Unit = {
    if (width) this.width.set(Dim(measured.width(), DimType.Auto))
    if (height) this.height.set(Dim(measured.height(), DimType.Auto))
  }
}