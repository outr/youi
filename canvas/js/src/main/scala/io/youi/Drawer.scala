package io.youi

import io.youi.component.Renderer
import io.youi.util.CanvasPool
import org.scalajs.dom._
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Drawer {
  def context: Context

  def prepare(width: Double, height: Double): Context
  def set(context: Context): Unit
  def update(width: Double, height: Double)(f: Context => Unit): Unit = {
    val context = prepare(width, height)
    f(context)
    set(context)
  }
  def updateAsync(width: Double, height: Double)(f: Context => Future[Unit]): Future[Unit] = {
    val context = prepare(width, height)
    val future = f(context)
    future.onComplete { _ =>
      set(context)
    }
    future
  }

  def dispose(): Unit
}

class RendererDrawer(val canvas: html.Canvas, renderer: Renderer) extends Drawer {
  override val context: Context = new Context(canvas)

  override def prepare(width: Double, height: Double): Context = {
    val w = math.ceil(width).toInt + 1
    val h = math.ceil(height).toInt + 1
    if (w != canvas.width || h != canvas.height) {
      canvas.width = w
      canvas.height = h
      val dpiWidth = w * renderer.dpiMultiplier
      val dpiHeight = h * renderer.dpiMultiplier
      canvas.style.width = s"${dpiWidth}px"
      canvas.style.height = s"${dpiHeight}px"
    } else {
      context.clear()
    }
    context
  }

  override def set(context: Context): Unit = {}

  override def dispose(): Unit = {}
}

class ComponentDrawer extends Drawer {
  private var _context: Context = new Context(CanvasPool(1, 1))
  override def context: Context = _context

  override def prepare(width: Double, height: Double): Context = {
    val c = CanvasPool(width + 1.0, height + 1.0)
    new Context(c)
  }

  override def set(context: Context): Unit = {
    CanvasPool.restore(_context.canvas)
    _context = context
  }

  override def dispose(): Unit = CanvasPool.restore(_context.canvas)
}