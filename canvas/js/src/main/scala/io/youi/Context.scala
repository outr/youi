package io.youi

import io.youi.component.Component
import io.youi.spatial.Matrix3
import org.scalajs.dom.{CanvasRenderingContext2D, html}

class Context(drawable: Drawable) {
  private def canvas: html.Canvas = drawable.canvas
  private lazy val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def transform(matrix: Matrix3): Unit = {
    context.setTransform(matrix.m00, matrix.m01, matrix.m10, matrix.m11, matrix.m02, matrix.m12)
  }

  def draw(component: Component): Unit = {
    val canvas = component.drawable.canvas
    context.drawImage(canvas.asInstanceOf[html.Image], 0.0, 0.0, canvas.width, canvas.height)
  }

  def clear(): Unit = {
    context.clearRect(0.0, 0.0, canvas.width, canvas.height)
  }
}
