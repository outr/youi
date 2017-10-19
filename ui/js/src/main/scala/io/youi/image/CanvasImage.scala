package io.youi.image

import io.youi.drawable.Context
import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom.html
import org.scalajs.dom.html.Canvas

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait CanvasImage extends Image {
  protected def canvas: html.Canvas
  override val width: Double = canvas.width
  override val height: Double = canvas.height

  override def draw(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = {
    context.drawCanvas(canvas)(x, y, width, height)
  }

  override def isVector: Boolean = false

  override def toDataURL: Future[String] = Future.successful(canvas.toDataURL("image/png"))

  override def dispose(): Unit = CanvasPool.restore(canvas)
}

object CanvasImage {
  def apply(canvas: html.Canvas, original: Option[html.Canvas] = None): CanvasImage = {
    val c = canvas
    new CanvasImage {
      override protected def canvas: Canvas = c
      override def resize(width: Double, height: Double): Future[Image] = {
        CanvasImage.resize(original.getOrElse(c), width, height)
      }
    }
  }

  def resize(canvas: html.Canvas, width: Double, height: Double): Future[CanvasImage] = {
    ImageUtility.drawToCanvas(canvas, CanvasPool(width, height))().map { resized =>
      apply(resized, Some(canvas))
    }
  }
}