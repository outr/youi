package io.youi.drawable

import io.youi._
import io.youi.util.CanvasPool
import org.scalajs.dom.html

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

class Cacheable extends Drawable {
  private var canvas: Option[html.Canvas] = None

  def width: Option[Double] = canvas.map(_.width.toDouble * (1.0 / ratio))
  def height: Option[Double] = canvas.map(_.height.toDouble * (1.0 / ratio))

  def sync(width: Double, height: Double)(f: Context => Unit): Unit = {
    val c = CanvasPool(width * ratio, height * ratio)
    val context = new Context(c, ratio)
    try {
      f(context)
    } finally {
      val old = canvas
      canvas = Some(c)
      old.foreach(CanvasPool.restore)
      modified @= System.currentTimeMillis()
    }
  }

  def invalidate(): Unit = {
    canvas.foreach(CanvasPool.restore)
    canvas = None
  }

  override def draw(context: Context, x: Double, y: Double): Unit = {
    context.save()
    context.scale(1.0 / context.ratioX, 1.0 / context.ratioY)
    canvas.foreach(context.drawCanvas(_)(x, y))
    context.restore()
  }
}