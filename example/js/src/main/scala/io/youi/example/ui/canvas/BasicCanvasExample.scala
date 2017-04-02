package io.youi.example.ui.canvas

import io.youi.example.ui.UIExampleScreen
import io.youi._
import io.youi.app.screen.CanvasScreen
import io.youi.canvas.{CanvasRenderer, ImageView, Texture}
import io.youi.hypertext.{Button, Canvas}
import io.youi.hypertext.border.BorderStyle

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object BasicCanvasExample extends UIExampleScreen with CanvasScreen {
  // TODO: remove once 4.4.4 is released
  CanvasRenderer.PixiJSURL = "https://cdnjs.cloudflare.com/ajax/libs/pixi.js/4.4.3/pixi.min.js"

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

    val renderer = CanvasRenderer(canvas)

    val texture = Texture("/images/bunny.png")
    val image = new ImageView
    image.texture := texture
    image.anchor.x := 0.5
    image.anchor.y := 0.5
    image.position.x := 200.0
    image.position.y := 150.0

    renderer.children += image

    renderer.start()

    ui.children += new Button {
      text := "Resize"

      event.click.attach { _ =>
        renderer.systemRenderer.resize(400, 400)
        AnimationFrame.nextFrame {
          canvas.updateSize()
        }
      }
    }
  }
}
