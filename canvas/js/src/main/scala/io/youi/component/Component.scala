package io.youi.component

import io.youi._
import io.youi.event.{Events, HitResult}
import io.youi.spatial.{Matrix3, MutableMatrix3, Point}
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import reactify._

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

trait Component extends TaskSupport with ComponentTheme {
  def theme: Var[_ <: ComponentTheme]

  lazy val id: Var[Option[String]] = Var(None)
  lazy val parent: Val[Option[AbstractContainer]] = Var(None)
  lazy val renderer: Val[Option[Renderer]] = Val(parent().flatMap(_.renderer()))
  val globalVisibility: Val[Boolean] = Val(visible() && parent().exists(_.globalVisibility()))

  lazy val event: Events = new Events(this)

  override protected def defaultThemeParent = Some(theme)

  protected[youi] lazy val drawer: Drawer = new Drawer()
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
    reMeasure(drawer.context)
    drawer.update(size.width(), size.height())(drawInternal)

    parent().foreach(_.invalidate())
  }

  protected def reDrawAsync(f: Context => Future[Unit]): Unit = {
    drawer.updateAsync(size.width(), size.height())(f).foreach { _ =>
      parent().foreach(_.invalidate())
    }
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

  object size {
    object measured {
      val width: Var[Double] = prop(0.0, updatesRendering = true)
      val height: Var[Double] = prop(0.0, updatesRendering = true)
    }

    def reset(width: Boolean = true, height: Boolean = true): Unit = {
      if (width) this.width.set(measured.width())
      if (height) this.height.set(measured.height())
    }

    val width: Var[Double] = prop(measured.width, updatesRendering = true)
    val height: Var[Double] = prop(measured.height, updatesRendering = true)

    lazy val center: Val[Double] = Val(width / 2.0)
    lazy val middle: Val[Double] = Val(height / 2.0)
  }

  object pivot {
    lazy val x: Var[Double] = prop(size.center(), updatesTransform = true)
    lazy val y: Var[Double] = prop(size.middle(), updatesTransform = true)
  }

  object offset {
    lazy val x: Var[Double] = prop(0.0, updatesRendering = true)
    lazy val y: Var[Double] = prop(0.0, updatesRendering = true)
  }

  protected def drawInternal(context: Context): Unit = {
    preDraw(context)
    draw(context)
    postDraw(context)
  }

  protected def preDraw(context: Context): Unit = {
    // Draw border and background
    border.draw(size.width, size.height, context, background)
    context.save()
    context.translate(offset.x, offset.y)
    context.translate(padding.left, padding.top)
    context.translate(border.size(Compass.West), border.size(Compass.North))
  }

  protected def draw(context: Context): Unit = {}

  protected def postDraw(context: Context): Unit = {
    context.restore()
  }

  override protected def updateTransform(): Unit = matrix.transform.flag()
  override protected def updateRendering(): Unit = invalidate()

  def hitTest(global: Point): HitResult = if (interactive() && visible()) {
    val local = localize(global)
    if (isHit(local)) {
      HitResult.Hit(local, this)
    } else {
      HitResult.Miss
    }
  } else {
    HitResult.Miss
  }

  def localize(global: Point): Point = {
    Component.tempPoint.set(global.x, global.y)
    val matrix = Component.tempMatrix.set(this.matrix.world).inv()
    matrix.localize(Component.tempPoint)
  }

  def isHit(local: Point): Boolean = local.x >= 0.0 && local.y >= 0.0 && local.x <= size.width() && local.y <= size.height()

  def invalidate(): Future[Unit] = {
    reDraw.flag()
    val promise = Promise[Unit]
    nextFrame {
      promise.success(())
    }
    promise.future
  }

  def updateMeasured(width: => Double, height: => Double): Unit = {
    size.measured.width := width + padding.width + border.width
    size.measured.height := height + padding.height + border.height
  }

  override def update(delta: Double): Unit = {
    super.update(delta)

    matrix.transform.update()
    reDraw.update()
  }

  override def updateTasks(): Boolean = super.updateTasks() && globalVisibility()

  override def toString = id().getOrElse(s"${getClass.getName}:$hashCode")
}

object Component extends ComponentTheme {
  private val tempMatrix = Matrix3.Identity.mutable
  private val tempPoint = Point.mutable()
}