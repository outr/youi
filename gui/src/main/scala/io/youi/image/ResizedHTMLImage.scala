package io.youi.image

import cats.effect.IO
import io.youi.image.resize.ImageResizer
import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom.html

class ResizedHTMLImage private(override protected val canvas: html.Canvas,
                               val original: HTMLImage,
                               resizer: ImageResizer) extends CanvasImage {
  override def resize(width: Double, height: Double): IO[Image] = if (this.width == width && this.height == height) {
    IO.pure(this)
  } else if (original.width == width && original.height == height) {
    IO.pure(original)
  } else {
    ResizedHTMLImage(original, width, height, resizer)
  }

  override def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): IO[html.Canvas] = {
    ResizedHTMLImage.resizeTo(original, canvas, width, height, resizer)
  }

  override def toString: String = s"ResizedHTMLImage($width x $height, original: $original, resizer: $resizer)"
}

object ResizedHTMLImage {
  def apply(original: HTMLImage, width: Double, height: Double, resizer: ImageResizer): IO[Image] = {
    val canvas = CanvasPool(width, height)
    ImageUtility.drawToCanvas(original.img, canvas, resizer)(0.0, 0.0, width, height).map(_ => new ResizedHTMLImage(canvas, original, resizer))
  }

  def resizeTo(image: HTMLImage, canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): IO[html.Canvas] = {
    ImageUtility.drawToCanvas(image.img, canvas, resizer)(0.0, 0.0, width, height)
  }
}