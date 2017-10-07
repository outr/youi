package io.youi.paint

import io.youi.drawable.Context
import org.scalajs.dom.CanvasPattern
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.scalajs.js

trait PatternPaint extends Paint {
  def createPattern(context: CanvasRenderingContext2D): CanvasPattern

  override def asJS(context: Context): js.Any = if (context.canvas.width > 0 && context.canvas.height > 0) {
    createPattern(context.ctx)
  } else {
    ""
  }
}
