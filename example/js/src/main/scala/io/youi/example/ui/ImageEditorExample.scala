package io.youi.example.ui

import io.youi._
import io.youi.component.editor.{AspectRatio, ImageEditor}
import io.youi.component.{Renderer, Texture}
import io.youi.example.ui.hypertext.HTMLScreen
import io.youi.hypertext.Canvas

import scala.concurrent.Future

object ImageEditorExample extends HTMLScreen {
  override def name: String = "Image Editor"
  override def path: String = "/examples/image-editor.html"

  private lazy val canvas = new Canvas {
    position.center := ui.position.center
    position.middle := ui.position.middle
    size.width := 800.0
    size.height := 500.0
  }
  private lazy val renderer = Renderer(canvas)

  override protected def load(): Future[Unit] = super.load().map { _ =>
    container.children += canvas

    val texture = Texture("/images/cuteness.jpg")

    // TODO: reset, zoom in/out, rotate left/right, upload

    val editor = new ImageEditor {
      image.texture := texture
//      image.rotation := 0.25
//      image.scale := 2.0
      aspectRatio := AspectRatio.Defined(16.0 / 12.0)
      size.width := canvas.size.width
      size.height := canvas.size.height
    }
    renderer.children += editor
  }
}