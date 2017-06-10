package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.DrawableComponent
import io.youi.component.layout.Snap
import io.youi.style.Paint

object SnapExample extends UIExampleScreen with UIScreen {
  override def name: String = "Snap"
  override def path: String = "/examples/snap.html"

  override def createUI(): Unit = {
    val box = new Box {
      background := Color.Black
      position.center := container.position.center
      position.middle := container.position.middle
    }

    val blue = Box(Color.Blue)
    Snap(blue).rightTo(box.position.left - 5.0)

    container.children += box
    container.children += blue
  }

  class Box extends DrawableComponent {
    size.width := 100.0
    size.height := 100.0
  }

  object Box {
    def apply(paint: Paint): Box = new Box {
      background := paint
    }
  }
}