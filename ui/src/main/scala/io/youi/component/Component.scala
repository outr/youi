package io.youi.component

import com.outr.pixijs._
import io.youi.{LazyUpdate, Updates}
import io.youi.component.event.Events
import io.youi.style.Theme
import reactify.{Dep, Val, Var}

trait Component extends Updates {
  protected[component] def instance: PIXI.Container
  protected def defaultTheme: Theme

  val transform = LazyUpdate(updateTransform())

  lazy val parent: Val[Option[Container]] = Var(None)

  val theme: Var[Theme] = prop(defaultTheme)
  val interactive: Var[Boolean] = prop(theme.interactive, instance.interactive = _)
  val visible: Var[Boolean] = prop(theme.visible, instance.visible = _, updatesRendering = true)

  lazy val event: Events = new Events(this)

  object position {
    lazy val x: Var[Double] = prop(instance.x, updatesTransform = true)
    lazy val y: Var[Double] = prop(instance.y, updatesTransform = true)

    lazy val left: Var[Double] = x
    lazy val center: Dep[Double, Double] = Dep(left, size.width / 2.0)
    lazy val right: Dep[Double, Double] = Dep(left, size.width)

    lazy val top: Var[Double] = y
    lazy val middle: Dep[Double, Double] = Dep(top, size.height / 2.0)
    lazy val bottom: Dep[Double, Double] = Dep(top, size.height)
  }

  lazy val rotation: Var[Double] = prop(0.0, updatesTransform = true)

  object scale {
    lazy val x: Var[Double] = prop(1.0, updatesTransform = true)
    lazy val y: Var[Double] = prop(1.0, updatesTransform = true)
  }

  object skew {
    lazy val x: Var[Double] = prop(0.0, updatesTransform = true)
    lazy val y: Var[Double] = prop(0.0, updatesTransform = true)
  }

  object size {
    object measured {
      lazy val width: Var[Double] = prop(0.0, updatesRendering = true)
      lazy val height: Var[Double] = prop(0.0, updatesRendering = true)
    }

    def reset(width: Boolean = true, height: Boolean = true): Unit = {
      if (width) this.width.set(measured.width())
      if (height) this.height.set(measured.height())
    }

    val width: Var[Double] = prop(measured.width(), updatesTransform = true)
    val height: Var[Double] = prop(measured.height(), updatesTransform = true)

    lazy val center: Val[Double] = Val(width / 2.0)
    lazy val middle: Val[Double] = Val(height / 2.0)    // TODO: diagnose why this isn't being updated properly
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
    v.attach { value =>
      set(value)
      if (updatesTransform) {
        transform.flag()
      }
      if (updatesRendering) {
        invalidate()
      }
    }
    v
  }

  override def update(delta: Double): Unit = {
    transform.update()

    super.update(delta)
  }

  protected def updateTransform(): Unit = {
    instance.width = size.width()
    instance.height = size.height()
    instance.setTransform(
      x = position.x() + pivot.x(),
      y = position.y() + pivot.y(),
      scaleX = scale.x(),
      scaleY = scale.y(),
      rotation = rotation() * (2.0 * -math.Pi),
      skewX = skew.x(),
      skewY = skew.y(),
      pivotX = pivot.x() / scale.x(),
      pivotY = pivot.y() / scale.y()
    )
    invalidate()
  }

  def invalidate(): Unit = parent().foreach(_.invalidate())
}