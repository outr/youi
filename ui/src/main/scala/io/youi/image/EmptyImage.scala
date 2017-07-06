package io.youi.image

import io.youi.component.Component
import org.scalajs.dom.html
import org.scalajs.dom.raw._

import scala.concurrent.Future

object EmptyImage extends Image {
  override val original: Option[Image] = None
  override val width: Double = 0.0
  override val height: Double = 0.0

  override def drawImage(component: Component,
                         canvas: html.Canvas,
                         context: CanvasRenderingContext2D,
                         width: Double,
                         height: Double): Future[Unit] = Future.successful(())

  override def resized(width: Double, height: Double): Future[Image] = Future.successful(this)

  override def dispose(): Unit = {}

  override def isVector: Boolean = true

  override def toDataURL: Future[String] = throw new RuntimeException("Empty image cannot be represented as a data url.")
}