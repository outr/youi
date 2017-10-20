package io.youi.image

import io.youi.image.resize.ImageResizer
import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom.html

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ResizedHTMLImage private(override protected val canvas: html.Canvas,
                               val original: HTMLImage,
                               resizer: ImageResizer) extends CanvasImage {
  override def resize(width: Double, height: Double): Future[Image] = if (this.width == width && this.height == height) {
    Future.successful(this)
  } else if (original.width == width && original.height == height) {
    Future.successful(original)
  } else {
    ResizedHTMLImage(original, width, height, resizer)
  }
}

object ResizedHTMLImage {
  def apply(original: HTMLImage, width: Double, height: Double, resizer: ImageResizer): Future[Image] = {
    val canvas = CanvasPool(width, height)
    ImageUtility.drawToCanvas(original.img, canvas, resizer)(0.0, 0.0, width, height).map(_ => new ResizedHTMLImage(canvas, original, resizer))
  }
}