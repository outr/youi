package youi.util

import rapid.Task
import youi._
import org.scalajs.dom.{CanvasRenderingContext2D, html}
import org.scalajs.dom.html.Canvas

object CanvasPool extends ObjectPool[html.Canvas] {
  def apply(width: Double, height: Double, ratio: Double = 1.0): html.Canvas = update(apply(), width * ratio, height * ratio)

  override protected def create(): Canvas = dom.create[html.Canvas]("canvas")

  private def update(canvas: html.Canvas, width: Double, height: Double): html.Canvas = {
    val w = math.ceil(width).toInt
    val h = math.ceil(height).toInt
    canvas.width = w
    canvas.height = h
    canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D].clearRect(0.0, 0.0, w, h)
    canvas
  }

  override def restore(canvas: html.Canvas): Unit = super.restore(canvas)

  def scale(ratio: Double): Double = 1.0 / ratio

  def withCanvas[R](width: Double, height: Double, ratio: Double = 1.0)(f: html.Canvas => R): R = use { canvas =>
    update(canvas, width * ratio, height * ratio)
    f(canvas)
  }

  def withCanvasTask[R](width: Double, height: Double, ratio: Double = 1.0)
                       (f: html.Canvas => Task[R]): Task[R] = io { canvas =>
    update(canvas, width * ratio, height * ratio)
    f(canvas)
  }
}