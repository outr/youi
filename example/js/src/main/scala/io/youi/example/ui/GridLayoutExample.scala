package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.Container
import io.youi.layout.GridLayout
import io.youi.paint.{Border, Paint, Stroke}
import reactify._

import scala.concurrent.Future

class GridLayoutExample extends UIScreen {
  override def title: String = "Grid Layout"
  override def path: String = "/examples/grid-layout.html"

  override def createUI(): Future[Unit] = {
    val grid = new Container {
      background := Color.AliceBlue
      padding := 15.0
      border := Border(Stroke(Color.Black))
      position.center := container.position.center
      position.middle := container.position.middle
    }
    grid.layout := new GridLayout {
      columns := 3
      config.default.margin.left := Some(10.0)
      config.cell(1, 1).margin.right := Some(10.0)
      config.cell(2, 0).span.columns := Some(2)
      config.cell(2, 0).alignment.horizontal := Some(Horizontal.Center)
    }

    grid.children ++= List(
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

    container.children += grid

    Future.successful(())
  }

  class Box extends Container {
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