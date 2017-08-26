package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.{Container, DrawableComponent, ScrollSupport}
import io.youi.layout.VerticalLayout
import io.youi.paint.Paint
import reactify._

object ScrollingExample extends UIExampleScreen with UIScreen {
  override def name: String = "Scrolling"
  override def path: String = "/examples/scrolling.html"

  override def createUI(): Unit = {

    val scrollable = new Container with ScrollSupport {
      id := Some("scrollable")
      size.width := container.size.width
      size.height := container.size.height
      background := Paint.vertical(container.size.height).distributeColors(Color.White, Color.Black)
      layoutManager := new VerticalLayout(25.0)

      event.pointer.attach(p => scribe.info(s"Pointer ${p.`type`} - $p"))
    }

    List(
      Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Purple, Color.Magenta, Color.Cyan,
      Color.DarkRed, Color.DarkGreen, Color.DarkBlue, Color.GreenYellow, Color.MediumPurple, Color.DarkCyan
    ).foreach { color =>
      val box = Box(color)
      scrollable.children += box
    }

    container.children += scrollable
  }

  class Box(w: Double, h: Double) extends DrawableComponent {
    interactive := false
    position.left := 25.0
    size.width := w
    size.height := h
  }

  object Box {
    def apply(paint: Paint, width: Double = 200.0, height: Double = 200.0): Box = new Box(width, height) {
      background := paint
    }
  }
}