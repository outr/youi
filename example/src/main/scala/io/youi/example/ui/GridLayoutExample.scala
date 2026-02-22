package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.Container
import io.youi.example.screen.UIExampleScreen
import spice.net._

class GridLayoutExample extends UIExampleScreen {
  override def title: String = "Grid Layout"
  override def path: URLPath = path"/examples/grid.html"

  private val boxSize = 100.0
  private val gap     = 10.0
  private val columns = 3
  private val colors  = List(
    Color.Black, Color.Red, Color.Green,
    Color.Blue, Color.Purple, Color.Pink,
    Color.Orange, Color.Yellow, Color.LightBlue,
    Color.Fuchsia
  )

  override def createUI(): Task[Unit] = Task {
    val rows       = math.ceil(colors.length.toDouble / columns).toInt
    val gridWidth  = columns * boxSize + (columns - 1) * gap
    val gridHeight = rows * boxSize + (rows - 1) * gap

    val grid = new Container {
      background @= Color.AliceBlue
      size.width  @= gridWidth + 30.0
      size.height @= gridHeight + 30.0
      position.center := container.size.center
      position.middle := container.size.middle

      colors.zipWithIndex.foreach { case (boxColor, i) =>
        val col = i % columns
        val row = i / columns
        val box = new Container {
          background @= boxColor
          size.width  @= boxSize
          size.height @= boxSize
          position.left @= 15.0 + col * (boxSize + gap)
          position.top  @= 15.0 + row * (boxSize + gap)
        }
        children += box
      }
    }
    container.children += grid
  }
}
