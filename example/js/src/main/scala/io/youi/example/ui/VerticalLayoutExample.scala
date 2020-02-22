package io.youi.example.ui

import io.youi._
import io.youi.component.Container
import io.youi.example.screen.UIExampleScreen
import io.youi.layout.VerticalLayout
import io.youi.net._
import io.youi.paint.Paint
import io.youi.style.{HTMLBorder, HTMLBorderStyle, Overflow}

import scala.concurrent.Future

class VerticalLayoutExample extends UIExampleScreen {
  override def title: String = "Vertical Layout"
  override def path: Path = path"/examples/vertical.html"

  override def createUI(): Future[Unit] = {
    component.overflow @= Overflow.Auto

    val boxes: Container = new Container {
      layout @= new VerticalLayout(spacing = 10.0)
      background @= Color.LightBlue
//      border := Border(Stroke(Color.Black), radius = 10.0)
      htmlBorder @= HTMLBorder(1.0, HTMLBorderStyle.Solid, Color.Black)
      htmlBorder.radius @= 10.0
      htmlBorder.radius.topLeft @= 15.0
      overflow @= Overflow.Hidden
      position.center := container.size.center
      position.middle := container.size.middle
      size.width @= 120.0
      size.height @= 475.0

      val black = Box(Color.Black)
      val red = Box(Color.Red)
      val green = Box(Color.Green)
      val blue = Box(Color.Blue)
      val orange = Box(Color.Orange)

      children ++= List(
        black,
        red,
        green,
        blue,
        orange
      )
    }
    container.children += boxes
    Future.successful(())
  }

  class Box extends Container {
    position.left @= 10.0
    size.width @= 100.0
    size.height @= 100.0
  }

  object Box {
    def apply(paint: Paint): Box = new Box {
      background @= paint
    }
  }
}