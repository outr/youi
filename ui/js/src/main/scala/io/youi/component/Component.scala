package io.youi.component

import io.youi.component.mixins.MatrixSupport
import io.youi.drawable.{Context, Drawable, Transformation}
import io.youi.event.Events
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import io.youi.{Compass, Modifiable, Unique, Updatable, Widget, WidgetPosition, WidgetSize}
import reactify._

import scala.annotation.tailrec

trait Component extends TaskSupport with ComponentTheme with Widget with MatrixSupport with Drawable { self =>
  def theme: Var[_ <: ComponentTheme]

  lazy val id: Var[String] = Var(Unique())
  lazy val event: Events = new Events(this)

  private val internalModified: Val[Long] = Val(modifiables.map(_.modified()).max)
  internalModified.attach(modified := _)
  private val internalUpdatables: Val[List[Updatable]] = Val(updatables)

  def parent: Val[Option[AbstractContainer]] = parentWidget.asInstanceOf[Val[Option[AbstractContainer]]]

  protected def modifiables: List[Modifiable] = List(background(), border().paint)
  protected def updatables: List[Updatable] = List(background(), border().paint)

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

  override final def draw(context: Context, x: Double, y: Double): Unit = {
    preDraw(context)
    drawInternal(context)
    postDraw(context)
    context.restore()
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
    context.save()
    context.opacity = context.opacity * opacity()
    border.background(size.width, size.height, context, background)
    val x = position.x + padding.left + border.size(Compass.West)
    val y = position.y + padding.top + border.size(Compass.North)
    Transformation.transform(context, x, y, pivot.x, pivot.y, rotation, manageState = false)(Drawable.None)
  }
  protected def drawInternal(context: Context): Unit = {}
  protected def postDraw(context: Context): Unit = {
    context.restore()
    border.draw(size.width, size.height, context)
  }

  override protected def defaultThemeParent = Some(theme)
  protected def determineActualVisibility: Boolean = visible() && parent().forall(_.visible())
  override def updateTasks(): Boolean = super.updateTasks() && actual.visibility
}

object Component extends ComponentTheme