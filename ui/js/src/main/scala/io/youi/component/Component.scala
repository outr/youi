package io.youi.component

import io.youi.component.mixins.{Interactivity, MatrixSupport}
import io.youi.drawable.{Cacheable, Context, Drawable, Transformation}
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import io.youi.{Compass, Modifiable, Unique, Updatable, Widget, WidgetPosition, WidgetSize, ui}
import reactify._

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Component extends TaskSupport with ComponentTheme with Widget with MatrixSupport with Interactivity with Drawable { self =>
  def theme: Var[_ <: ComponentTheme]

  lazy val id: Var[String] = Var(s"${`type`}.${Unique(length = 4, characters = Unique.Readable).toLowerCase}")
  def `type`: String

  private val internalModified: Val[Long] = Val(modifiables.map(_.modified()).max)
  private val internalUpdatables: Val[List[Updatable]] = Val(updatables)

  def parent: Val[Option[AbstractContainer]] = parentWidget.asInstanceOf[Val[Option[AbstractContainer]]]

  protected def modifiables: List[Modifiable] = List(background(), border().paint)
  protected def updatables: List[Updatable] = List(background(), border().paint)

  private lazy val cacheable: Cacheable = new Cacheable

  override protected def init(): Unit = {
    super.init()

    internalModified.attach(modified := _)
  }

  object actual {
    val x: Val[Double] = Val(parent().map(_.actual.x()).getOrElse(0.0) + position.x())
    val y: Val[Double] = Val(parent().map(_.actual.y()).getOrElse(0.0) + position.y())
    val visibility: Val[Boolean] = Val(determineActualVisibility)
    val opacity: Val[Double] = Val(self.opacity() * parent().map(_.actual.opacity()).getOrElse(1.0))
  }

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

  lazy val rotation: Var[Double] = prop(0.0, updatesTransform = true)

  override final def draw(context: Context, x: Double, y: Double): Unit = if (cache()) {
    if (cacheable.modified() < modified()) {
      cacheable.sync(size.width, size.height) { ctx =>
        draw(ctx, Transform.None)
      }
    }
    // TODO: rotate and pivot
    cacheable.draw(context, x + (position.x * ui.ratio), y + (position.y * ui.ratio))
  } else {
    draw(context, Transform.All)
  }

  def draw(context: Context, transform: Transform): Unit = {
    context.save()
    transformDraw(context, transform)
    preDraw(context)
    drawInternal(context)
    postDraw(context)
    context.restore()
    borderDraw(context, transform)
  }

  override def update(delta: Double): Unit = {
    super.update(delta)

    updateUpdatables(delta, internalUpdatables())
  }

  protected def updateMeasured(width: => Double, height: => Double): Unit = {
    size.measured.width := width + padding.width + border.width
    size.measured.height := height + padding.height + border.height
  }

  @tailrec
  private def updateUpdatables(delta: Double, updatables: List[Updatable]): Unit = if (updatables.nonEmpty) {
    updatables.head.update(delta)
    updateUpdatables(delta, updatables.tail)
  }

  protected def preDraw(context: Context): Unit = {
    context.opacity = context.opacity * opacity()
    border.background(size.width, size.height, context, background)
    Transformation.transform(
      context = context,
      x = padding.left + border.size(Compass.West),
      y = padding.top + border.size(Compass.North)
    )()
  }
  protected def transformDraw(context: Context, transform: Transform): Unit = {
    val (tx, ty, px, py, r) = transform match {
      case Transform.None => (0.0, 0.0, 0.0, 0.0, 0.0)
      case Transform.Translate => (position.x(), position.y(), 0.0, 0.0, 0.0)
      case Transform.NoTranslate => (0.0, 0.0, pivot.x(), pivot.y(), rotation())
      case Transform.All => (position.x(), position.y(), pivot.x(), pivot.y(), rotation())
      case Transform.Override(tx, ty, px, py, r) => (tx, ty, px, py, r)
    }
    Transformation.transform(context, tx, ty, px, py, r, manageState = false)()
  }
  protected def drawInternal(context: Context): Unit
  protected def postDraw(context: Context): Unit = {
  }
  protected def borderDraw(context: Context, transform: Transform): Unit = if (border.paint.nonEmpty) {
    context.save()
    transformDraw(context, transform)
    border.draw(size.width, size.height, context)
    context.restore()
  }

  override protected def defaultThemeParent = Some(theme)
  protected def determineActualVisibility: Boolean = visible() && parent().forall(_.actual.visibility())
  override def updateTasks(): Boolean = super.updateTasks() && actual.visibility

  override protected def updateRendering(): Unit = {
    super.updateRendering()

    invalidate()
  }

  override protected def updateTransform(): Unit = {
    super.updateTransform()

    updateMatrix()
    parent().foreach(_.updateRendering())
  }

  override def invalidate(): Future[Unit] = super.invalidate().map { _ =>
    modified := System.currentTimeMillis()
  }

  override def toString: String = id()
}

object Component extends ComponentTheme

sealed trait Transform

object Transform {
  case object None extends Transform
  case object Translate extends Transform
  case object NoTranslate extends Transform
  case object All extends Transform
  case class Override(tx: Double = 0.0,
                      ty: Double = 0.0,
                      px: Double = 0.0,
                      py: Double = 0.0,
                      rotation: Double = 0.0) extends Transform
}