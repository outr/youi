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
      position.left := 550.0
      position.top := 250.0
//      rotation := 45.degrees
    }

    val selection = new RectangularSelection {
      selection.position.x := 100.0
      selection.position.y := 100.0
      selection.size.width := 500.0
      selection.size.height := 300.0
      selection.stroke := Color.Red
      selection.lineWidth := 2.0
    }
    container.children += selection
  }
}