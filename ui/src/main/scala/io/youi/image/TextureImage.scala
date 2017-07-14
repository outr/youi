package io.youi.image

import io.youi.component.Component
import io.youi.util.ImageUtility
import org.scalajs.dom.{CanvasRenderingContext2D, html}

import scala.concurrent.Future

case class TextureImage(img: html.Image,
                        width: Double,
                        height: Double,
                        original: Option[Image]) extends Image {
  override def isVector: Boolean = false

  override def drawImage(component: Component,
                         canvas: html.Canvas,
                         context: CanvasRenderingContext2D,
                         width: Double,
                         height: Double): Future[Unit] = {
    if (img.width.toDouble == width && img.height.toDouble == height) {     // Draw directly
      context.drawImage(img, 0.0, 0.0, width, height)
      Future.successful(())
    } else {
      ImageUtility.drawToCanvas(img, canvas)(width = width, height = height).map(_ => ())
    }
  }

  override def resized(width: Double, height: Double, dropOriginal: Boolean): Future[Image] = {
    val original = this.original.getOrElse(this)
    Future.successful(copy(width = width, height = height, original = if (dropOriginal) None else Some(original)))
  }

  override def toDataURL: Future[String] = ImageUtility.resizeToDataURL(img, width, height)

  override def dispose(): Unit = {}
}