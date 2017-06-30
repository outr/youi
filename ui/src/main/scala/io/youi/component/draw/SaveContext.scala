package io.youi.component.draw
import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.concurrent.Future

object SaveContext extends Drawable {
  override def draw(component: Component, context: CanvasRenderingContext2D): Future[Unit] = {
    context.save()
    Future.successful(())
  }

  override def boundingBox: BoundingBox = BoundingBox.zero
}
