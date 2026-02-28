package youi.example.ui

import rapid.Task
import youi.*
import youi.Color
import youi.component.support.SingletonThemedComponent
import youi.component.{CanvasView, TextView}
import youi.component.types.PositionType
import youi.drawable.Context
import youi.event.EventSupport
import youi.example.screen.UIExampleScreen
import youi.ui.margin
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