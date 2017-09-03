package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.DrawableComponent
import io.youi.layout.{GridLayout, VerticalLayout}
import io.youi.paint.Paint
import reactify._

object GridLayoutExample extends UIExampleScreen with UIScreen {
  override def name: String = "Grid Layout"
  override def path: String = "/examples/grid-layout.html"

  override def createUI(): Unit = {
    container.layout := new GridLayout {
      columns := 3
//      config.default.margin.left := Some(10.0)
      config.default.alignment.horizontal := Some(Horizontal.Center)
//      config.cell(1, 1).margin.right := Some(10.0)
      config.cell(2, 0).span.columns := Some(2)
//      config.cell(2, 0).alignment.horizontal := Some(Horizontal.Center)
    }

    container.children ++= List(
      Box(Color.Black),
      Box(Color.Red),
      Box(Color.Green),
      Box(Color.Blue),
      Box(Color.Purple),
      Box(Color.Pink),
      Box(Color.Orange),
      Box(Color.Yellow),
      Box(Color.LightBlue),
      Box(Color.Fuchsia)
    )
  }

  class Box extends DrawableComponent {
    position.left := 10.0
    size.measured.width := 100.0
    size.measured.height := 100.0
  }

  object Box {
    def apply(paint: Paint): Box = new Box {
      background := paint
    }
  }
}