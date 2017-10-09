package io.youi.drawable

import io.youi.ui
import io.youi.util.CanvasPool
import org.scalajs.dom.html

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

trait Cacheable extends Drawable {
  private var canvas: Option[html.Canvas] = None

  protected def updateCache(width: Double, height: Double)(f: Context => Future[Unit]): Unit = {
    val c = CanvasPool(width * ui.ratio, height * ui.ratio)
    val context = new Context(c, ui.ratio)
    val future = f(context)
    future.onComplete {
      case Success(_) => {
        val old = canvas
        canvas = Some(c)
        old.foreach(CanvasPool.restore)
        modified := System.currentTimeMillis()
      }
      case Failure(t) => {
        scribe.error(t)
        CanvasPool.restore(c)
      }
    }
  }

  override def draw(context: Context, x: Double, y: Double): Unit = {
    context.save()
    context.scale(1.0 / context.ratioX, 1.0 / context.ratioY)
    canvas.foreach(context.drawCanvas(_)(x, y))
    context.restore()
  }
}