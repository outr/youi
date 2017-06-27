package io.youi.image

import io.youi.component.Component
import org.scalajs.dom.{CanvasRenderingContext2D, html}

case class TextureImage(img: html.Image,
                        canvas: Option[html.Canvas],
                        width: Double,
                        height: Double,
                        original: Option[Image]) extends Image {
  override def drawImage(component: Component, context: CanvasRenderingContext2D, width: Double, height: Double): Unit = {
    context.drawImage(canvas.getOrElse(img).asInstanceOf[html.Image], 0.0, 0.0, width, height)
  }
}