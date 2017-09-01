package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.{BasicText, Container, ScrollSupport}
import io.youi.layout.VerticalLayout
import io.youi.paint.{Paint, Stroke}
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
    }

    Color.all.foreach { color =>
      val box = Box(color)
      scrollable.children += box
      box
    }

    container.children += scrollable
  }

  class Box(val text: String, color: Color, w: Double, h: Double) extends Container {
    background := color
    children += new BasicText {
      value := text
      fill := Color.White
      stroke := Stroke(Color.Black)
      font.size := 48.0
      position.left := 20.0
      position.top := 10.0
    }
    interactive := false
    position.left := 25.0
    size.width := w
    size.height := h

    override def toString: String = text
  }

  object Box {
    def apply(color: Color, width: Double = 500.0, height: Double = 200.0): Box = {
      new Box(Color.name(color).get, color, width, height)
    }
  }
}