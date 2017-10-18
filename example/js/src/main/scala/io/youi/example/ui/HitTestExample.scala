package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.{Contained, Container}

import scala.concurrent.Future

object HitTestExample extends UIExampleScreen with UIScreen {
  override def name: String = "Hit Test"
  override def path: String = "/examples/hit-test.html"

  override def createUI(): Future[Unit] = Future.successful {
    container.children += Contained(
      new Box(Color.Cyan) {                       // Top-Left
        position.left := 50.0
        position.top := 50.0
      },
      new Box(Color.Magenta) {                    // Top-Right
        position.right := ui.width - 50.0
        position.top := 50.0
        opacity := 0.5
      },
      new Box(Color.Yellow) {                     // Bottom-Left
        position.left := 50.0
        position.bottom := ui.height - 50.0
      },
      new Box(Color.Black) {                      // Bottom-Right
        position.right := ui.width - 50.0
        position.bottom := ui.height - 50.0
      },
      new Box(Color.DarkRed) {                    // Center
        position.center := ui.center
        position.middle := ui.middle
        size.width := 200.0
        size.height := 200.0

        children += new Box(Color.Green) {
          position.x := 100.0
          position.y := 100.0
        }
      }
    )
  }

  class Box(color: Color) extends Container {
    background := color.withAlpha(0.5)
    size.width := 100.0
    size.height := 100.0

    event.pointer.overState.attach {
      case true => background := color
      case false => background := color.withAlpha(0.5)
    }
  }
}