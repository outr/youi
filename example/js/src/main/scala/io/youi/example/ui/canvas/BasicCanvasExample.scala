package io.youi.example.ui.canvas

import io.youi.example.ui.UIExampleScreen
import io.youi._
import io.youi.app.screen.CanvasScreen
import io.youi.component.{Image, Renderer, Texture}
import io.youi.hypertext.{Button, Canvas}
import io.youi.hypertext.border.BorderStyle

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object BasicCanvasExample extends UIExampleScreen with CanvasScreen {
  override def name: String = "Basic Canvas Example"
  override def path: String = "/examples/basic_canvas.html"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val canvas = new Canvas {
      border.style := Some(BorderStyle.Solid)
      border.size := Some(2.0)
      border.color := Some(Color.DarkBlue)
      border.radius := 5.0
      position.center := ui.position.center
      position.middle := ui.position.middle
      size.width := 1024.0
      size.height := 768.0
    }
    ui.children += canvas

    val renderer = Renderer(canvas)

    val texture = Texture("/images/bunny.png")
    val image = new Image(texture) {
      position.bottom := renderer.size.height - 10.0
      position.right := renderer.size.width - 10.0
    }

    renderer.children += image

    ui.children += new Button {
      text := "Resize"

      event.click.attach { _ =>
        renderer.size.width := 400.0
        renderer.size.height := 400.0
      }
    }
  }
}
