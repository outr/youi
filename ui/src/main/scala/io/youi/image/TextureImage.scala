package io.youi.image

import io.youi._
import io.youi.component.Component
import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom.{CanvasRenderingContext2D, html}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class TextureImage(img: html.Image,
                        canvas: Option[html.Canvas],
                        width: Double,
                        height: Double,
                        original: Option[Image]) extends Image {
  override def drawImage(component: Component,
                         canvas: html.Canvas,
                         context: CanvasRenderingContext2D,
                         width: Double,
                         height: Double): Future[Unit] = Future.successful {
    context.drawImage(this.canvas.getOrElse(img).asInstanceOf[html.Image], 0.0, 0.0, width, height)
  }

  override def resized(width: Double, height: Double): Future[Image] = if (this.width == width && this.height == height) {
    Future.successful(this)
  } else if (original.map(_.width).contains(width) && original.map(_.height).contains(height)) {
    Future.successful(original.get)
  } else {
    val canvas = CanvasPool(width, height)
    val context = canvas.context
    context.clearRect(0.0, 0.0, width, height)
    ImageUtility.resizeToCanvas(img, canvas).map { c =>
      val original = this.original.getOrElse(this)
      copy(canvas = Some(c), width = width, height = height, original = Some(original))
    }
  }

  override def dispose(): Unit = canvas.foreach(CanvasPool.restore)
}