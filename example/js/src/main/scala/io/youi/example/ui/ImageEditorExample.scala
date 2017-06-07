package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.editor.ImageEditor
import io.youi.component.extra.RectangularSelection
import io.youi.component.{Image, Texture}

object ImageEditorExample extends UIExampleScreen with UIScreen {
  override def name: String = "Image Editor"
  override def path: String = "/examples/image-editor.html"

  override def createUI(): Unit = {
    val texture = Texture("/images/cuteness.jpg")

    // TODO: reset, zoom in/out, rotate left/right, upload

    val editor = new ImageEditor {
      image.texture := texture
      position.center := container.position.center
      position.middle := container.position.middle
    }
    container.children += editor
  }
}