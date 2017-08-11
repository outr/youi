package io.youi

import org.scalajs.dom.{CanvasRenderingContext2D, html}

class Context(drawable: Drawable) {
  private def canvas: html.Canvas = drawable.canvas
  private lazy val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def clear(): Unit = {
    context.clearRect(0.0, 0.0, canvas.width, canvas.height)
  }
}
