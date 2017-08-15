package io.youi.component

import io.youi._
import io.youi.paint.Paint
import io.youi.spatial.{Matrix3, MutableMatrix3, Point}
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import org.scalajs.dom.raw.MouseEvent
import reactify.{Dep, Val, Var}

trait Component extends TaskSupport {
  def theme: Var[_ <: ComponentTheme]

  lazy val parent: Val[Option[AbstractContainer]] = Var(None)
  lazy val renderer: Val[Option[Renderer]] = Val(parent().flatMap(_.renderer()))
  val visible: Var[Boolean] = prop(theme.visible, updatesRendering = true)
  val globalVisibility: Val[Boolean] = Val(visible() && parent().exists(_.globalVisibility()))
  val background: Var[Paint] = prop(theme.background, updatesRendering = true)
  val cursor: Var[Cursor] = prop(theme.cursor)
  val interactive: Var[Boolean] = prop(theme.interactive)

//  lazy val event: Events = new Events(this)

  protected[youi] lazy val drawable: Drawable = new Drawable()
  object matrix {
    val local: MutableMatrix3 = Matrix3.Identity.mutable
    val world: MutableMatrix3 = Matrix3.Identity.mutable
    val transform = LazyUpdate {
      local.set(Matrix3.Identity)
      local.translate(position.x(), position.y())
      local.translate(pivot.x(), pivot.y())
      local.rotate(rotation())
      local.translate(-pivot.x(), -pivot.y())

      world.set(parent().map(_.matrix.world).getOrElse(Matrix3.Identity))
      world *= local

      parent().foreach(_.invalidate())
    }
  }
  lazy val reDraw = LazyUpdate {
    reMeasure(drawable.context)
    drawable.update(size.width(), size.height())(draw)

    parent().foreach(_.invalidate())
  }

  protected def reMeasure(context: Context): Unit = {}

  reDraw.flag()

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

  def draw(context: Context): Unit = {
    // Draw background
    if (background().nonEmpty) {
      context.rect(0.0, 0.0, size.width(), size.height())
      context.fill(background(), apply = true)
    }
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
        matrix.transform.flag()
      }
      if (updatesRendering) {
        invalidate()
      }
    }
    v
  }

  def hitTest(evt: MouseEvent): Option[Point] = {
    val matrix = Component.tempMatrix.set(this.matrix.world).inv()
    val local = matrix.localize(Component.tempPoint)
    if (local.x >= 0.0 && local.y >= 0.0 && local.x <= size.width() && local.y <= size.height()) {
      Some(local)
    } else {
      None
    }
  }

  def invalidate(): Unit = reDraw.flag()

  override def update(delta: Double): Unit = {
    super.update(delta)

    matrix.transform.update()
    reDraw.update()
  }

  override def updateTasks(): Boolean = super.updateTasks() && globalVisibility()
}

object Component extends ComponentTheme {
  private val tempMatrix = Matrix3.Identity.mutable
  private val tempPoint = Point.mutable()
}