package io.youi.image

import io.youi.component.Component
import org.scalajs.dom.raw._

object EmptyImage extends Image {
  override val original: Option[Image] = None
  override val width: Double = 0.0
  override val height: Double = 0.0

  override def drawImage(component: Component, context: CanvasRenderingContext2D, width: Double, height: Double): Unit = {}
}