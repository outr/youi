package io.youi.image

import io.youi.{Context, ImageMode}
import io.youi.util.ImageUtility
import org.scalajs.dom.html

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class TextureImage(img: html.Image,
                        width: Double,
                        height: Double,
                        mode: ImageMode) extends Image {
  override def isVector: Boolean = false

  override def drawFast(context: Context, width: Double, height: Double): Boolean = {
    context.drawImage(img)(width = width, height = height)
    (this.width != width || this.height != height) && mode == ImageMode.Quality
  }

  override def draw(context: Context, width: Double, height: Double): Future[Unit] = {
    if (this.width == width && this.height == height) {     // Draw directly
      drawFast(context, width, height)
      Future.successful(())
    } else {
      ImageUtility.drawToCanvas(img, context.canvas)(width = width, height = height).map(_ => ())
    }
  }

  override def toDataURL: Future[String] = ImageUtility.toDataURL(img)

  override def dispose(): Unit = {}
}