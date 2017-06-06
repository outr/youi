package io.youi.component
import io.youi.ItemContainer
import io.youi.component.draw.Drawable
import io.youi.style.Paint
import io.youi.theme.DrawableComponentTheme
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

import scala.scalajs.js

class DrawableComponent extends CanvasComponent with PaintSupport {
  override lazy val theme: Var[_ <: DrawableComponentTheme] = Var(DrawableComponent)

  val preDraw: ItemContainer[Drawable] = new ItemContainer[Drawable]()
  val postDraw: ItemContainer[Drawable] = new ItemContainer[Drawable]()
  val drawable: Var[Drawable] = Var(Drawable.empty)

  val background: Var[Paint] = Var(theme.background)

  drawable.on(reDraw.flag())
  fill.value.on(reDraw.flag())
  stroke.value.on(reDraw.flag())

  size.measured.width := drawable().boundingBox.width
  size.measured.height := drawable().boundingBox.height

  override protected def paintTheme: PaintTheme = theme()

  override protected def draw(context: CanvasRenderingContext2D): Unit = {
    background() match {
      case paint if paint == Paint.none => // No background
      case paint => {
        context.fillStyle = paint(this).asInstanceOf[js.Any]
        context.fillRect(0.0, 0.0, size.width(), size.height())
      }
    }

    preDraw.foreach(_.draw(this, context))
    fill.value.set(this, context)
    stroke.value.set(this, context)
    drawable().draw(this, context)
    if (autoPaint) {
      fill.value.fill(context)
      stroke.value.stroke(context)
    }
    postDraw.foreach(_.draw(this, context))
  }

  protected def autoPaint: Boolean = true
}

object DrawableComponent extends DrawableComponentTheme