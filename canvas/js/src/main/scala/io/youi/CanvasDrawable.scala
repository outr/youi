package io.youi

import io.youi.drawable.{Context, Drawable}
import org.scalajs.dom._

class CanvasDrawable extends Drawable with Context {
  lazy val canvas: html.Canvas = dom.create[html.Canvas]("canvas")
  private lazy val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  override def update(width: Double, height: Double)(f: (Context) => Unit): Unit = {
    canvas.width = math.ceil(width).toInt
    canvas.height = math.ceil(height).toInt
    context.clearRect(0.0, 0.0, canvas.width, canvas.height)
    f(this)
  }
}