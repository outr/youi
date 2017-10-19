package io.youi.image

import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom.html

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ResizedHTMLImage private(override protected val canvas: html.Canvas, val original: HTMLImage) extends CanvasImage {
  override def resize(width: Double, height: Double): Future[Image] = {
    ResizedHTMLImage(original, width, height)
  }
}

object ResizedHTMLImage {
  def apply(original: HTMLImage, width: Double, height: Double): Future[Image] = {
    val canvas = CanvasPool(width, height)
    ImageUtility.drawToCanvas(original.img, canvas)().map(_ => new ResizedHTMLImage(canvas, original))
  }
}