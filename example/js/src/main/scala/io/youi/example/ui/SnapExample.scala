package io.youi.example.ui

import io.youi._
import io.youi.component.Container
import io.youi.example.screen.UIExampleScreen
import io.youi.layout.snap.Snap
import io.youi.net._
import io.youi.paint.Paint
import reactify._

import scala.concurrent.Future

class SnapExample extends UIExampleScreen {
  override def title: String = "Snap"
  override def path: Path = path"/examples/snap.html"

  override def createUI(): Future[Unit] = {
    val box = new Box {
      background := Color.Black
      position.center := container.size.center
      position.middle := container.size.middle
    }

    val blue = Box(Color.Blue)
    Snap(blue).rightTo(box.position.left - 5.0).topTo(box.position.top)

    val green = Box(Color.Green)
    Snap(green).leftTo(blue.position.left).rightTo(box.position.right).topTo(box.position.bottom + 5.0)

    val yellow = Box(Color.Yellow.withAlpha(0.5))
    Snap(yellow).leftTo(blue.position.center).rightTo(box.position.center).topTo(box.position.middle)

    container.children ++= List(
      box,
      blue,
      green,
      yellow
    )

    Future.successful(())
  }

  class Box extends Container {
    size.width := 100.0
    size.height := 100.0
  }

  object Box {
    def apply(paint: Paint): Box = new Box {
      background := paint
    }
  }
}