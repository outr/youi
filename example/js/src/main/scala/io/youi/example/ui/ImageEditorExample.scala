package io.youi.example.ui

import io.youi._
import io.youi.component.editor.{AspectRatio, ImageEditor}
import io.youi.component.{Renderer, Texture}
import io.youi.example.ui.hypertext.HTMLScreen
import io.youi.hypertext.{Button, Canvas}
import reactify._

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

    val editor = new ImageEditor {
      image.texture := texture
      aspectRatio := AspectRatio.Defined(16.0 / 12.0)
      size.width := canvas.size.width
      size.height := canvas.size.height
      event.pointer.pinch.attach { evt =>
        scale(evt.deltaDistance * 0.001)
      }
    }
    renderer.children += editor

    val resetButton = new Button {
      text := "Reset"
      position.left := canvas.position.left
      position.top := canvas.position.bottom + 20.0

      event.click.on(editor.reset())
    }

    val rotateLeft = new Button {
      text := "Rotate Left"
      position.left := resetButton.position.right + 20.0
      position.top := resetButton.position.top

      event.click.on(editor.rotate(-0.25))
    }

    val rotateRight = new Button {
      text := "Rotate Right"
      position.left := rotateLeft.position.right + 20.0
      position.top := resetButton.position.top

      event.click.on(editor.rotate(0.25))
    }

    val zoomIn = new Button {
      text := "Zoom In"
      position.left := rotateRight.position.right + 20.0
      position.top := resetButton.position.top

      event.click.on(editor.scale(0.1))
    }

    val zoomOut = new Button {
      text := "Zoom Out"
      position.left := zoomIn.position.right + 20.0
      position.top := resetButton.position.top

      event.click.on(editor.scale(-0.1))
    }

    // TODO: upload

    container.children ++= List(
      resetButton, rotateLeft, rotateRight, zoomIn, zoomOut
    )
  }
}