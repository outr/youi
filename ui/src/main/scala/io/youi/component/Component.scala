package io.youi.component

import com.outr.pixijs._
import io.youi.{Compass, Horizontal, LazyUpdate, Vertical}
import io.youi.component.event.Events
import io.youi.style.Cursor
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import reactify._

trait Component extends TaskSupport {
  protected[component] def instance: PIXI.Container

  val transform = LazyUpdate(updateTransform())

  lazy val parent: Val[Option[AbstractContainer]] = Var(None)
  lazy val parentRenderer: Val[Option[Renderer]] = Var(parent.flatMap(_.parentRenderer))

  def theme: Var[_ <: ComponentTheme]
  val cursor: Var[Cursor] = prop(theme.cursor, c => instance.cursor = c.value)
  val interactive: Var[Boolean] = prop(theme.interactive, instance.interactive = _)
  val visible: Var[Boolean] = prop(theme.visible, instance.visible = _, updatesRendering = true)
  val globalVisibility: Val[Boolean] = Val(visible() && parent().exists(_.globalVisibility()))

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
    val horizontal = scale.direction().horizontal
    val vertical = scale.direction().vertical
    val scaleAdjustX = horizontal match {
      case Horizontal.Left => 0.0
      case Horizontal.Center => (size.width / 2.0) - ((size.width * scale.x) / 2.0)
      case Horizontal.Right => size.width - (size.width * scale.x)
    }
    val scaleAdjustY = vertical match {
      case Vertical.Top => 0.0
      case Vertical.Middle => (size.height / 2.0) - ((size.height * scale.y) / 2.0)
      case Vertical.Bottom => size.height - (size.height * scale.y)
    }
    instance.setTransform(
      x = position.x() + pivot.x() + scaleAdjustX,
      y = position.y() + pivot.y() + scaleAdjustY,
      scaleX = scale.x(),
      scaleY = scale.y(),
      rotation = rotation() * (2.0 * math.Pi),
      skewX = skew.x(),
      skewY = skew.y(),
      pivotX = pivot.x() / scale.x(),
      pivotY = pivot.y() / scale.y()
    )
    invalidate()
  }

  def invalidate(): Unit = parent().foreach(_.invalidate())

  override def updateTasks(): Boolean = super.updateTasks() && globalVisibility()
}

object Component extends ComponentTheme