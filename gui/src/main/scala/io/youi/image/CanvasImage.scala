package io.youi.image

import rapid.Task
import io.youi.drawable.Context
import io.youi.image.resize.ImageResizer
import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom.html
import org.scalajs.dom.html.Canvas

trait CanvasImage extends Image {
  protected def canvas: html.Canvas
  override val width: Double = canvas.width
  override val height: Double = canvas.height

  override def draw(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = {
    context.drawCanvas(canvas)(x, y, width, height)
  }

  override def isVector: Boolean = false

  override def toDataURL: Task[String] = Task.pure(canvas.toDataURL("image/png"))

  override def dispose(): Unit = CanvasPool.restore(canvas)

  override def toString: String = s"CanvasImage($width x $height)"
}

object CanvasImage {
  def canvasFor(image: CanvasImage): html.Canvas = image.canvas

  def apply(canvas: html.Canvas, resizer: ImageResizer, original: Option[html.Canvas] = None): CanvasImage = {
    val c = canvas
    new CanvasImage {
      override protected def canvas: Canvas = c
      override def resize(width: Double, height: Double): Task[Image] = if (this.width == width && this.height == height) {
        Task.pure(this)
      } else if (original.map(_.width.toDouble).contains(width) && original.map(_.height.toDouble).contains(height)) {
        Task(apply(original.get, resizer, None))
      } else {
        CanvasImage.resize(original.getOrElse(c), width, height, resizer)
      }

      override def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): Task[html.Canvas] = {
        val source: html.Canvas = original.getOrElse(c)
        ImageUtility.drawToCanvas(source, canvas, resizer)(0.0, 0.0, width, height)
      }
    }
  }

  def resize(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): Task[CanvasImage] = {
    ImageUtility.drawToCanvas(canvas, CanvasPool(width, height), resizer)().map { resized =>
      apply(resized, resizer, Some(canvas))
    }
  }
}
