package io.youi.example.ui

import io.youi._
import io.youi.component.editor.{AspectRatio, ImageEditor}
import io.youi.component.{Renderer, Texture}
import io.youi.hypertext.ImageView
import io.youi.example.ui.hypertext.HTMLScreen
import io.youi.hypertext.border.BorderStyle
import io.youi.hypertext.{Button, Canvas}
import io.youi.util.ImageUtility
import reactify._

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

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
      event.gestures.pinch.attach { evt =>
        scale(evt.deltaDistance * 0.01, Some(evt.pointer.move.local))
      }
    }
    renderer.children += editor

    val preview1 = new ImageView {
      position.left := canvas.position.right + 10.0
      position.top := canvas.position.top
      size.width := 160.0
      size.height := 120.0
      border.size := Some(1.0)
      border.style := Some(BorderStyle.Solid)
      border.color := Some(Color.Black)
    }
    ui.children += preview1

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

    val previewUpdate = LazyUpdate({
      scribe.info(s"Resizing....")
      ImageUtility.resizeToImage(editor.preview.source, 160.0, 120.0, preview1.element).onComplete {
        case Success(image) => {
          scribe.info("Updated successfully!")
          preview1.updateSize()
        }
        case Failure(throwable) => scribe.error(throwable)
      }
    }, 250.millis)
    editor.delta.on(previewUpdate.update())
    editor.revision.on(previewUpdate.flag())
  }
}