package io.youi.component.extras

import io.youi._
import io.youi.component.Component
import io.youi.style.{Length, PixelLength}
import reactify.{Val, Var}

trait ComponentSize {
  protected def component: Component

  object measured {
    lazy val width: Var[Double] = Var(0.0)
    lazy val height: Var[Double] = Var(0.0)
  }
  object actual {
    lazy val width: Val[Double] = Var {
      ComponentSize.this.width() match {
        case Length.default => measured.width
        case l: PixelLength => l.value
      }
    }
    lazy val height: Val[Double] = Var {
      ComponentSize.this.height() match {
        case Length.default => measured.height
        case l: PixelLength => l.value
      }
    }
    lazy val center: Val[Double] = Val(width / 2.0)
    lazy val middle: Val[Double] = Val(height / 2.0)
  }

  def reset(width: Boolean = true, height: Boolean = true): Unit = {
    if (width) this.width.set(measured.width())
    if (height) this.height.set(measured.height())
  }

  def width: Var[Length]
  def height: Var[Length]

  lazy val center: Val[Length] = Val(width / 2.0)
  lazy val middle: Val[Length] = Val(height / 2.0)
}