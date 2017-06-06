package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.extra.RectangularSelection
import io.youi.component.{Image, Texture}

object ImageEditorExample extends UIExampleScreen with UIScreen {
  override def name: String = "Image Editor"
  override def path: String = "/examples/image-editor.html"

  override def createUI(): Unit = {
    val texture = Texture("/images/cuteness.jpg")

    // TODO: reset, zoom in/out, rotate left/right, upload

    container.children += new Image(texture) {
      position.left := 550.0
      position.top := 250.0
    }

    val selection = new RectangularSelection {
      position.x := 100.0
      position.y := 100.0
      size.width := 800.0
      size.height := 600.0

      selection.set(100.0, 100.0, 300.0, 300.0)
      selection.stroke.lineWidth := 2.0
      selection.aspectRatio.bySize(625.0, 352.0)
    }
    container.children += selection
  }
}