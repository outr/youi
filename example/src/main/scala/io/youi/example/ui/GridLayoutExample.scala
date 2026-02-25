package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.Container
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import io.youi.layout.{GridLayout, LayoutSupport}
import spice.net._

class GridLayoutExample extends UIExampleScreen {
  override def title: String = "Grid Layout"
  override def path: URLPath = path"/examples/grid.html"

  private val colors = List(
    Color.Black, Color.Red, Color.Green,
    Color.Blue, Color.Purple, Color.Pink,
    Color.Orange, Color.Yellow, Color.LightBlue,
    Color.Fuchsia
  )

  override def createUI(): Task[Unit] = Task {
    val grid = new Container with LayoutSupport {
      layout @= Some(GridLayout(columns = 3, horizontalSpacing = 10.0, verticalSpacing = 10.0))
      backgroundColor := ExampleApp.subtleBg
      size.width @= 340.0
      position.center := container.size.center
      position.top @= 20.0

      colors.foreach { boxColor =>
        val box = new Container {
          background @= boxColor
          size.height @= 100.0
        }
        children += box
      }
    }
    container.children += grid
  }
}
