package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.extra.RectangularSelection
import io.youi.component.{Image, Texture}

object ImageEditorExample extends UIExampleScreen with UIScreen {
  override def name: String = "Image Editor"
  override def path: String = "/examples/image-editor.html"

  override def createUI(): Unit = {
    val texture = Texture("/cuteness.jpg")

    // TODO: reset, zoom in/out, rotate left/right, upload

    container.children += new Image(texture) {
      position.left := 250.0
      position.top := 250.0
      rotation := math.Pi.radians
    }

    val selection = new RectangularSelection {
      size.width.and(size.height).on {
        scribe.info(s"Selection Size: ${size.width()}x${size.height()}")
      }
      x1 := 100.0
      y1 := 100.0
      x2 := 500.0
      y2 := 300.0
      stroke := Color.Red
      lineWidth := 2.0
    }
    container.children += selection
  }
}