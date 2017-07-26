package io.youi.util

import io.youi._
import org.scalajs.dom.html
import org.scalajs.dom.html.Canvas

import scala.concurrent.Future

object CanvasPool extends ObjectPool[html.Canvas] {
  def apply(width: Double, height: Double): html.Canvas = update(apply(), width, height)

  override protected def create(): Canvas = dom.create[html.Canvas]("canvas")

  private def update(canvas: html.Canvas, width: Double, height: Double): html.Canvas = {
    val w = math.ceil(width).toInt
    val h = math.ceil(height).toInt
    canvas.width = w
    canvas.height = h
    canvas.context.clearRect(0.0, 0.0, w, h)
    canvas
  }

  override def restore(canvas: html.Canvas): Unit = super.restore(canvas)

  def withCanvas[R](width: Double, height: Double)(f: html.Canvas => R): R = use { canvas =>
    update(canvas, width, height)
    f(canvas)
  }

  def withCanvasFuture[R](width: Double, height: Double)(f: html.Canvas => Future[R]): Future[R] = future { canvas =>
    update(canvas, width, height)
    f(canvas)
  }
}