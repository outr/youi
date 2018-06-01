package io.youi.example.ui

import io.youi.Color
import io.youi.component.CanvasComponent
import io.youi.drawable.Context
import io.youi.example.screen.UIExampleScreen

import scala.concurrent.Future

class CanvasExample extends UIExampleScreen {
  override def title: String = "Canvas Example"
  override def path: String = "/examples/canvas.html"

  override def createUI(): Future[Unit] = Future.successful {
    val canvas = new CanvasComponent {
      position.center := container.size.center
      position.middle := container.size.middle

      size.width := 500.0
      size.height := 500.0

      override protected def draw(context: Context): Unit = {
        context.rect(0.0, 0.0, 500.0, 500.0)
        context.fill(Color.Red, apply = true)
        context.rect(100.0, 100.0, 300.0, 300.0)
        context.fill(Color.DarkRed, apply = true)
        context.fill(Color.White, apply = false)
        context.setFont("Arial", 72.0, "normal", "normal", "normal")
        context.fillText("Hello, World!", 50.0, 220.0)
      }
    }
    container.children += canvas
  }
}
