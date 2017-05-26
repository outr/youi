package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.{Image, Texture}

object ImageEditorExample extends UIExampleScreen with UIScreen {
  override def name: String = "Image Editor"
  override def path: String = "/examples/image-editor.html"

  override def createUI(): Unit = {
    val texture = Texture("/cuteness.jpg")

    container.children += new Image(texture) {
      position.left := 250.0
      position.top := 250.0
      rotation := math.Pi.radians
    }
  }
}