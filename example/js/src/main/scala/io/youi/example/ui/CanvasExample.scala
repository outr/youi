package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component._
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.concurrent.Future

object CanvasExample extends UIExampleScreen with UIScreen {
  override def name: String = "Canvas Example"
  override def path: String = "/examples/canvas.html"

  override def createUI(): Unit = {
    var canvasColor = "red"
    val canvas = new CanvasComponent {
      position.x := 10.0
      position.y := 10.0
      size.width := 400.0
      size.height := 400.0

      override protected def draw(context: CanvasRenderingContext2D): Future[Unit] = {
        context.fillStyle = canvasColor
        context.fillRect(0.0, 0.0, size.width(), size.height())
        context.fillStyle = "black"
        context.font = "64px Arial"
        context.fillText("Hello, World!", 20.0, 100.0)
        Future.successful(())
      }
    }
    canvas.event.click.on {
      if (canvasColor == "red") {
        canvasColor = "blue"
      } else {
        canvasColor = "red"
      }
      canvas.reDraw.flag()
    }
    container.children += canvas
  }
}
