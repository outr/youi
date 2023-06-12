package io.youi.example.ui

import cats.effect.IO
import io.youi.Color
import io.youi.component.CanvasView
import io.youi.component.support.PositionSupport
import io.youi.drawable.Context
import io.youi.example.screen.UIExampleScreen
import spice.net._

class CanvasExample extends UIExampleScreen {
  override def title: String = "Canvas Example"
  override def path: URLPath = path"/examples/canvas.html"

  override def createUI(): IO[Unit] = IO {
    val canvas = new CanvasView with PositionSupport {
      position.center := container.size.center
      position.middle := container.size.middle

      width @= 500
      height @= 500

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