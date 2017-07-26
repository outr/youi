package io.youi.component.draw
import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.concurrent.Future

case class Translate(x: Double = 0.0, y: Double = 0.0) extends Drawable {
  override def boundingBox: BoundingBox = BoundingBox.zero

  override def draw(component: Component, context: CanvasRenderingContext2D): Future[Unit] = {
    context.translate(x, y)
    Future.successful(())
  }
}