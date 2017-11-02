package io.youi.example.ui

import io.youi._
import io.youi.component.editor.{AspectRatio, ImageEditor}
import io.youi.example.ClientExampleApplication
import io.youi.example.ui.hypertext.HTMLScreen
import io.youi.hypertext.border.BorderStyle
import io.youi.hypertext.{Button, Canvas, ImageView, TextInput}
import io.youi.model.ImageEditorInfo
import org.scalajs.dom._
import reactify._

import scala.concurrent.Future

object ImageEditorExample extends HTMLScreen {
  override def name: String = "Image Editor"
  override def path: String = "/examples/image-editor.html"

  private lazy val canvas = new Canvas(manageSize = false) {
    position.center := ui.center
    position.middle := ui.middle
    size.width := 800.0
    size.height := 500.0
  }
  private lazy val renderer = new Renderer(canvas.element, 800.0, 500.0)

  override protected def load(): Future[Unit] = super.load().map { _ =>
    container.children += canvas

    val editor = new ImageEditor {
      aspectRatio := AspectRatio.Defined(16.0 / 12.0)
      size.width := canvas.size.width
      size.height := canvas.size.height

      load("/images/cuteness.jpg")
    }
    renderer.drawable := editor
    renderer.visible := true

    val preview1 = new ImageView {
      id := "preview1"
      position.left := canvas.position.right + 10.0
      position.top := canvas.position.top
      size.width := 160.0
      size.height := 120.0
      border.size := Some(1.0)
      border.style := Some(BorderStyle.Solid)
      border.color := Some(Color.Black)
      editor.previewImage(element, 160.0, 120.0)
      visible := editor.actual.visibility
    }
    container.children += preview1

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

    val fit = new Button {
      text := "Fit Image"
      position.left := zoomOut.position.right + 20.0
      position.top := resetButton.position.top

      event.click.on(editor.fit())
    }

    val original = new Button {
      text := "Original Size"
      position.left := fit.position.right + 20.0
      position.top := resetButton.position.top

      event.click.on(editor.original())
    }

    val saved = Var[Option[ImageEditorInfo]](None)
    val save = new Button {
      text := "Save State"
      position.left := original.position.right + 20.0
      position.top := resetButton.position.top

      event.click.on(saved.static(Some(editor.info)))
    }
    val load = new Button {
      text := "Load State"
      position.left := save.position.right + 20.0
      position.top := resetButton.position.top

      event.click.on {
        saved() match {
          case Some(info) => {
            scribe.info(s"Loading state: $info...")
            editor.info = info
          }
          case None => scribe.info("No information saved!")
        }
      }
    }

    val fileUpload = new TextInput {
      position.left := load.position.right + 20.0
      position.top := resetButton.position.top
      element.`type` = "file"
      element.addEventListener("change", (evt: Event) => {
        if (element.files.length > 0) {
          val file = element.files(0)
          editor.load(file)
        }
      })
    }

    container.children ++= List(
      resetButton, rotateLeft, rotateRight, zoomIn, zoomOut, fit, original, save, load, fileUpload
    )
  }


  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    ClientExampleApplication.debug.renderer := renderer
    renderer.visible := true
  }

  override protected def deactivate(): Future[Unit] = super.deactivate().map { _ =>
    renderer.visible := false
    ClientExampleApplication.debug.renderer := Renderer
  }
}