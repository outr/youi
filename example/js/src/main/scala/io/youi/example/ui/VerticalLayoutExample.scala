package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.DrawableComponent
import io.youi.component.layout.VerticalLayout
import io.youi.style.Paint
import reactify._

object VerticalLayoutExample extends UIExampleScreen with UIScreen {
  override def name: String = "Vertical Layout"
  override def path: String = "/examples/vertical-layout.html"

  override def createUI(): Unit = {
    container.layout := new VerticalLayout(spacing = 10.0, fromTop = true)

    val black = Box(Color.Black)
    val red = Box(Color.Red)
    val green = Box(Color.Green)
    val blue = Box(Color.Blue)

    container.children ++= List(
      black,
      red,
      green,
      blue
    )
  }

  class Box extends DrawableComponent {
    position.left := 10.0
    size.width := 100.0
    size.height := 100.0
  }

  object Box {
    def apply(paint: Paint): Box = new Box {
      background := paint
    }
  }
}