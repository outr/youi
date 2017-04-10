package io.youi.example.ui.canvas

import io.youi.example.ui.UIExampleScreen
import io.youi.app.screen.UIScreen
import io.youi.component.{Image, Texture}
import io.youi.workflow._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object BasicCanvasExample extends UIExampleScreen with UIScreen {
  override def name: String = "Basic Canvas Example"
  override def path: String = "/examples/basic_canvas.html"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val texture = Texture("/images/icon.png")

    renderer.children += new Image(texture) {     // Top-Left
      position.left := 50.0
      position.top := 50.0
    }
    renderer.children += new Image(texture) {     // Top-Right
      position.right := renderer.position.right - 50.0
      position.top := 50.0
    }
    renderer.children += new Image(texture) {     // Bottom-Left
      position.left := 50.0
      position.bottom := renderer.position.bottom - 50.0
    }
    renderer.children += new Image(texture) {     // Bottom-Right
      position.right := renderer.position.right - 50.0
      position.bottom := renderer.position.bottom - 50.0
    }
    renderer.children += new Image(texture) {     // Center
      position.center := renderer.position.center
      position.middle := renderer.position.middle

      forever {
        rotation to 1.0 in 1.seconds andThen(rotation := 0.0)
      }.start()
    }
  }
}
