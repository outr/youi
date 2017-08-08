package io.youi.component

import io.youi._
import io.youi.drawable.Drawable
import reactify.{Dep, Val, Var}

class Component {
  private lazy val drawable: Drawable = ui.createDrawable()

  object position {
    lazy val x: Var[Double] = prop(0.0, updatesTransform = true)
    lazy val y: Var[Double] = prop(0.0, updatesTransform = true)

    lazy val left: Var[Double] = x
    lazy val center: Dep[Double, Double] = Dep(left, size.width / 2.0)
    lazy val right: Dep[Double, Double] = Dep(left, size.width)

    lazy val top: Var[Double] = y
    lazy val middle: Dep[Double, Double] = Dep(top, size.height / 2.0)
    lazy val bottom: Dep[Double, Double] = Dep(top, size.height)
  }

  lazy val rotation: Var[Double] = prop(0.0, updatesTransform = true)

  object scale {
    def :=(value: => Double): Unit = {
      x := value
      y := value
    }

    lazy val x: Var[Double] = prop(1.0, updatesTransform = true)
    lazy val y: Var[Double] = prop(1.0, updatesTransform = true)
    lazy val direction: Var[Compass] = prop(Compass.NorthWest, updatesTransform = true)
  }

  object skew {
    lazy val x: Var[Double] = prop(0.0, updatesTransform = true)
    lazy val y: Var[Double] = prop(0.0, updatesTransform = true)
  }

  object size {
    object measured {
      val width: Var[Double] = prop(0.0, updatesRendering = true)
      val height: Var[Double] = prop(0.0, updatesRendering = true)
    }

    def reset(width: Boolean = true, height: Boolean = true): Unit = {
      if (width) this.width.set(measured.width())
      if (height) this.height.set(measured.height())
    }

    val width: Var[Double] = prop(measured.width, updatesTransform = true)
    val height: Var[Double] = prop(measured.height, updatesTransform = true)

    lazy val center: Val[Double] = Val(width / 2.0)
    lazy val middle: Val[Double] = Val(height / 2.0)
  }

  object pivot {
    lazy val x: Var[Double] = prop(size.center(), updatesTransform = true)
    lazy val y: Var[Double] = prop(size.middle(), updatesTransform = true)
  }

  protected[youi] def prop[T](get: => T,
                              set: T => Unit = (_: T) => (),
                              updatesTransform: Boolean = false,
                              updatesRendering: Boolean = false): Var[T] = {
    val v = Var[T](get)
    set(v())
    v.attach { value =>
      set(value)
//      if (updatesTransform) {
//        transform.flag()
//      }
//      if (updatesRendering) {
//        invalidate()
//      }
    }
    v
  }
}