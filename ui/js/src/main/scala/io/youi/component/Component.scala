package io.youi.component

import io.youi.drawable.{Context, Drawable}
import io.youi.event.Events
import io.youi.task.TaskSupport
import io.youi.theme.ComponentTheme
import io.youi.{Unique, Widget, WidgetPosition, WidgetSize}
import reactify._

trait Component extends TaskSupport with ComponentTheme with Widget with Drawable { self =>
  def theme: Var[_ <: ComponentTheme]

  lazy val id: Var[String] = Var(Unique())
  lazy val event: Events = new Events(this)

  def parent: Val[Option[AbstractContainer]] = parentWidget.asInstanceOf[Val[Option[AbstractContainer]]]

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

  override final def draw(context: Context, x: Double, y: Double): Unit = {
    preDraw(context)
    drawInternal(context)
    postDraw(context)
  }

  protected def preDraw(context: Context): Unit = {}
  protected def drawInternal(context: Context): Unit = {}
  protected def postDraw(context: Context): Unit = {}

  override protected def defaultThemeParent = Some(theme)
  protected def determineActualVisibility: Boolean = visible() && parent().exists(_.actual.visibility())
}

object Component extends ComponentTheme