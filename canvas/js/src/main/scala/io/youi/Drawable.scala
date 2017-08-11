package io.youi

import org.scalajs.dom._

class Drawable(private[youi] val canvas: html.Canvas = dom.create[html.Canvas]("canvas")) {
  // TODO: support swapping canvases on each update to avoid potential render flickering
  private lazy val context = new Context(this)

  def update(width: Double, height: Double)(f: Context => Unit): Unit = {
    canvas.width = math.ceil(width).toInt
    canvas.height = math.ceil(height).toInt

    context.clear()
    f(context)
  }
}