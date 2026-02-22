package io.youi.example.ui

import rapid.Task
import io.youi.*
import io.youi.Color
import io.youi.component.support.SingletonThemedComponent
import io.youi.component.{CanvasView, TextView}
import io.youi.component.types.PositionType
import io.youi.drawable.Context
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.ui.margin
import org.scalajs.dom.window
import spice.net.*

class CanvasExample extends UIExampleScreen {
  override def title: String = "Canvas Example"
  override def path: URLPath = path"/examples/canvas.html"

  override def createUI(): Task[Unit] = Task {
    val canvas = new CanvasView with EventSupport {
      position.`type` @= PositionType.Absolute
      position.center := container.size.center
      position.middle := container.size.middle

      event.click.on {
        val h = window.innerHeight
        val mt = margin.top
        val mb = margin.bottom
        scribe.info(s"UI: ${ui.size.width()}x${ui.size.height()}, Container: ${container.size.width()}x${container.size.height()}, Component: ${width()}x${height()}, Position: ${position.x}x${position.y}, Height: $h, $mt, $mb")
      }

      width @= 500.0
      height @= 500.0

      override protected def draw(context: Context): Unit = {
        context.rect(0.0, 0.0, 500.0, 500.0)
        context.fill(Color.Red, true)
        context.rect(100.0, 100.0, 300.0, 300.0)
        context.fill(Color.DarkRed, true)
        context.fill(Color.White, false)
        context.setFont("Arial", 72.0, "normal", "normal", "normal")
        context.fillText("Hello, World!", 50.0, 220.0)
      }
    }
    container.children += canvas
  }
}