package io.youi.component

import com.outr.pixijs._
import io.youi.Updates
import reactify.{Dep, Val, Var}

trait Component extends Updates {
  protected[component] def instance: PIXI.Container

  lazy val parent: Val[Option[Container]] = Var(None)

  object position {
    lazy val x: Var[Double] = prop(instance.x, (d: Double) => instance.x = d)
    lazy val y: Var[Double] = prop(instance.y, (d: Double) => instance.y = d)

    lazy val left: Var[Double] = x
    lazy val center: Dep[Double, Double] = Dep(left, size.width / 2.0)
    lazy val right: Dep[Double, Double] = Dep(left, size.width)

    lazy val top: Var[Double] = y
    lazy val middle: Dep[Double, Double] = Dep(top, size.height / 2.0)
    lazy val bottom: Dep[Double, Double] = Dep(top, size.height)
  }

  lazy val rotation: Var[Double] = prop(0.0, (d: Double) => instance.rotation = d * (2.0 * math.Pi))

  object scale {
    lazy val x: Var[Double] = Var(1.0)
    lazy val y: Var[Double] = Var(1.0)
  }

  object size {
    object measured {
      lazy val width: Var[Double] = Var(0.0)
      lazy val height: Var[Double] = Var(0.0)
    }

    object width extends Var[Double](() => measured.width()) {
      def reset(): Unit = set(measured.width())
    }
    object height extends Var[Double](() => measured.height()) {
      def reset(): Unit = set(measured.height())
    }

    lazy val center: Val[Double] = Val(width / 2.0)
    lazy val middle: Val[Double] = Val(height / 2.0)
  }

  size.width.attach(_ => updateSize())
  size.height.attach(_ => updateSize())
  scale.x.attach(_ => updateSize())
  scale.y.attach(_ => updateSize())

  protected[youi] def prop[T](get: => T, set: T => Unit): Var[T] = {
    val v = Var[T](get)
    v.attach(set)
    v
  }

  protected def updateSize(): Unit = {
    instance.width = size.width()
    instance.height = size.height()
    instance.scale.x = scale.x()
    instance.scale.y = scale.y()
  }
}