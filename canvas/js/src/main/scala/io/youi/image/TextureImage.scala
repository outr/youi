package io.youi.image

import io.youi.Context
import io.youi.util.ImageUtility
import org.scalajs.dom.html

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class TextureImage(img: html.Image,
                        width: Double,
                        height: Double,
                        original: Option[Image]) extends Image {
  override def isVector: Boolean = false

  override def drawImage(context: Context): Future[Unit] = {
    if (img.width.toDouble == width && img.height.toDouble == height) {     // Draw directly
      context.draw(img)(width = width, height = height)
      Future.successful(())
    } else {
      ImageUtility.drawToCanvas(img, context.canvas)(width = width, height = height).map(_ => ())
    }
  }

  override def resized(width: Double, height: Double, dropOriginal: Boolean): Future[Image] = {
    val original = this.original.getOrElse(this)
    Future.successful(copy(width = width, height = height, original = if (dropOriginal) None else Some(original)))
  }

  override def toDataURL: Future[String] = ImageUtility.resizeToDataURL(img, width, height)

  override def dispose(): Unit = {}
}