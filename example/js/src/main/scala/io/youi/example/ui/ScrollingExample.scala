package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.{Container, DrawableComponent}
import io.youi.layout.VerticalLayout
import io.youi.paint.Paint
import reactify._

object ScrollingExample extends UIExampleScreen with UIScreen {
  override def name: String = "Scrolling"
  override def path: String = "/examples/scrolling.html"

  override def createUI(): Unit = {
    val scrollable = new Container {
      id := Some("scrollable")
      size.width := 250.0
      size.height := 250.0
      background := Paint.vertical(250.0).distributeColors(Color.White, Color.Black)
      layoutManager := new VerticalLayout(10.0)

      event.pointer.wheel.attach { evt =>
        val value = offset.y - evt.delta.y
        val min = 0
        val max = -(size.measured.height() - size.height())
        offset.y.static(math.max(max, math.min(min, value)))

        evt.htmlEvent.stopPropagation()
        evt.htmlEvent.preventDefault()
      }
    }

    List(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Purple, Color.Magenta, Color.Cyan).foreach { color =>
      val box = Box(color)
      scrollable.children += box
    }

    container.children += scrollable
  }

  class Box(w: Double, h: Double) extends DrawableComponent {
    interactive := false
    position.left := 10.0
    size.width := w
    size.height := h
  }

  object Box {
    def apply(paint: Paint, width: Double = 100.0, height: Double = 100.0): Box = new Box(width, height) {
      background := paint
    }
  }
}