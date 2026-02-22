package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.Container
import io.youi.component.support.{PositionSupport, SizeSupport}
import io.youi.example.screen.UIExampleScreen
import io.youi.paint.Paint
import spice.net._

class SnapExample extends UIExampleScreen {
  override def title: String = "Snap"
  override def path: URLPath = path"/examples/snap.html"

  override def createUI(): Task[Unit] = Task {
    val box = new Box {
      background @= Color.Black
      position.center := container.size.center
      position.middle := container.size.middle
    }

    val blue = Box(Color.Blue)
    blue.position.right := box.position.left - 5.0
    blue.position.top   := box.position.top

    val green = Box(Color.Green)
    green.position.left  := blue.position.left
    green.position.right := box.position.right
    green.position.top   := box.position.bottom + 5.0

    val yellow = Box(Color.Yellow.withAlpha(0.5))
    yellow.position.left  := blue.position.center
    yellow.position.right := box.position.center
    yellow.position.top   := box.position.middle

    container.children ++= List(box, blue, green, yellow)
  }

  class Box extends Container with SizeSupport with PositionSupport {
    size.width  @= 100.0
    size.height @= 100.0
  }

  object Box {
    def apply(paint: Paint): Box = new Box {
      background @= paint
    }
  }
}
