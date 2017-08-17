package io.youi

import io.youi.util.CanvasPool
import org.scalajs.dom._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Drawable(private[youi] var canvas: html.Canvas = dom.create[html.Canvas]("canvas"),
               swapCanvases: Boolean = true) {
  private[youi] var context = new Context(canvas)

  def update(width: Double, height: Double)(f: Context => Unit): Unit = {
    val c = if (swapCanvases) {
      CanvasPool(width, height)
    } else {
      val w = math.ceil(width).toInt
      val y = math.ceil(height).toInt
      if (w != canvas.width || y != canvas.height) {
        canvas.width = w
        canvas.height = y
      } else {
        context.clear()
      }
      canvas
    }
    try {
      if (swapCanvases) {
        context = new Context(c)
      }
      f(context)
    } finally {
      if (swapCanvases) {
        CanvasPool.restore(canvas)
        canvas = c
      }
    }
  }

  def updateAsync(width: Double, height: Double)(f: Context => Future[Unit]): Future[Unit] = {
    val c = if (swapCanvases) {
      CanvasPool(width, height)
    } else {
      val w = math.ceil(width).toInt
      val y = math.ceil(height).toInt
      if (w != canvas.width || y != canvas.height) {
        canvas.width = w
        canvas.height = y
      } else {
        context.clear()
      }
      canvas
    }
    if (swapCanvases) {
      context = new Context(c)
    }
    f(context).map { _ =>
      if (swapCanvases) {
        CanvasPool.restore(canvas)
        canvas = c
      }
    }
  }
}