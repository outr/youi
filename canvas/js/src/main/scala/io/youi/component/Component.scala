package io.youi.component

import io.youi._
import io.youi.event.{Events, HitResult}
import io.youi.paint.Paint
import io.youi.spatial.{Matrix3, MutableMatrix3, Point}
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import reactify._

import scala.annotation.tailrec
import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

trait Component extends TaskSupport with ComponentTheme with Widget { self =>
  def theme: Var[_ <: ComponentTheme]

  lazy val id: Var[Option[String]] = Var(None)
  def parent: Val[Option[AbstractContainer]] = parentWidget.asInstanceOf[Val[Option[AbstractContainer]]]
  lazy val renderer: Val[Option[Renderer]] = Val(parent().flatMap(_.renderer()))

  object actual {
    val x: Val[Double] = Val(parent().map(_.actual.x()).getOrElse(0.0) + position.x())
    val y: Val[Double] = Val(parent().map(_.actual.y()).getOrElse(0.0) + position.y())
    val visibility: Val[Boolean] = Val(determineActualVisibility)
    val opacity: Val[Double] = Val(self.opacity() * parent().map(_.actual.opacity()).getOrElse(1.0))
  }

  protected[youi] val modified: Var[Long] = Var(System.currentTimeMillis())

  protected def determineActualVisibility: Boolean = self.visible() && parent().exists(_.actual.visibility())

  lazy val event: Events = new Events(this)

  override protected def defaultThemeParent = Some(theme)

  protected[youi] lazy val drawer: Drawer = new ComponentDrawer

  protected def paints: List[Paint] = List(background(), border().paint)

  object matrix {
    val local: MutableMatrix3 = Matrix3.Identity.mutable
    val world: MutableMatrix3 = Matrix3.Identity.mutable
    val transform = LazyUpdate(calculateMatrices())
  }
  lazy val reDraw = LazyUpdate {
    reMeasure(drawer.context)
    if (shouldDraw) {
      drawer.update(size.width * ui.dpiMultiplier, size.height * ui.dpiMultiplier)(drawInternal)
    }

    parent().foreach(_.invalidate())
  }

  protected def shouldDraw: Boolean = true

  protected def calculateMatrices(): Unit = {
    matrix.local.set(Matrix3.Identity)
    matrix.local.translate(position.x(), position.y())
    matrix.local.translate(pivot.x(), pivot.y())
    matrix.local.rotate(rotation())
    matrix.local.translate(-pivot.x(), -pivot.y())

    matrix.world.set(parent().map(_.matrix.world).getOrElse(Matrix3.Identity))
    matrix.world *= matrix.local

    parent().foreach(_.invalidate())
  }

  protected def reDrawAsync(f: Context => Future[Unit]): Unit = {
    drawer.updateAsync(size.width * ui.dpiMultiplier, size.height * ui.dpiMultiplier) { context =>
      preDraw(context)
      f(context).map { _ =>
        postDraw(context)
      }
    }.foreach { _ =>
      parent().foreach(_.invalidate())
    }
  }

  protected def reMeasure(context: Context): Unit = {}

  reDraw.flag()

  object position extends WidgetPosition {
    override lazy val x: Var[Double] = prop(0.0, updatesTransform = true)
    override lazy val y: Var[Double] = prop(0.0, updatesTransform = true)

    override lazy val left: Var[Double] = x
    override lazy val center: Dep[Double, Double] = Dep(left, size.width / 2.0)
    override lazy val right: Dep[Double, Double] = Dep(left, size.width)

    override lazy val top: Var[Double] = y
    override lazy val middle: Dep[Double, Double] = Dep(top, size.height / 2.0)
    override lazy val bottom: Dep[Double, Double] = Dep(top, size.height)
  }

  lazy val rotation: Var[Double] = prop(0.0, updatesTransform = true)

  object size extends WidgetSize {
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

  init()

  override protected def init(): Unit = {
    super.init()

    (actual.x and actual.y).on(matrix.transform.flag())
  }

  protected def drawInternal(context: Context): Unit = try {
    preDraw(context)
    draw(context)
    postDraw(context)
  } finally {
    modified := System.currentTimeMillis()
  }

  def drawToParent(parent: AbstractContainer, parentContext: Context): Unit = parentContext.draw(this)

  protected def preDraw(context: Context): Unit = {
    // Set opacity
    context.opacity = actual.opacity

    // Draw border and background
    context.save()
    preScale(context)
    border.background(size.width, size.height, context, background)
    context.translate(offset.x, offset.y)
    context.translate(padding.left, padding.top)
    context.translate(border.size(Compass.West), border.size(Compass.North))
  }

  protected def preScale(context: Context): Unit = {
    val scale = ui.dpiMultiplier()
    context.scale(scale, scale)
  }

  protected def draw(context: Context): Unit = {}

  protected def postDraw(context: Context): Unit = {
    context.restore()
    border.draw(size.width, size.height, context)
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

  override def invalidate(): Future[Unit] = super.invalidate().flatMap { _ =>
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

  private lazy val paintsVal = Val(paints)

  override def update(delta: Double): Unit = {
    super.update(delta)

    matrix.transform.update()
    updatePaints(delta, paintsVal)
    reDraw.update()
  }

  @tailrec
  private def updatePaints(delta: Double, paints: List[Paint]): Unit = if (paints.nonEmpty) {
    val paint = paints.head
    paint.update(delta)
    if (paint.modified > modified()) {
      reDraw.flag()
    }
    updatePaints(delta, paints.tail)
  }

  override def updateTasks(): Boolean = super.updateTasks() && actual.visibility

  override def toString: String = id().getOrElse(s"${getClass.getName}:$hashCode")
}

object Component extends ComponentTheme {
  private val tempMatrix = Matrix3.Identity.mutable
  private val tempPoint = Point.mutable()
}