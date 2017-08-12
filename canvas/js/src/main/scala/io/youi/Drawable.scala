package io.youi

import io.youi.util.CanvasPool
import org.scalajs.dom._

class Drawable(private[youi] var canvas: html.Canvas = dom.create[html.Canvas]("canvas"),
               swapCanvases: Boolean = true) {
  private var context = new Context(canvas)

  def update(width: Double, height: Double)(f: Context => Unit): Unit = {
    val c = if (swapCanvases) {
      CanvasPool(width, height)
    } else {
      canvas.width = math.ceil(width).toInt
      canvas.height = math.ceil(height).toInt

      context.clear()
      canvas
    }
    if (swapCanvases) {
      context = new Context(c)
    }
    f(context)
    if (swapCanvases) {
      CanvasPool.restore(canvas)
      canvas = c
    }
  }
}