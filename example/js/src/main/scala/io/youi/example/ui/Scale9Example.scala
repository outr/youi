package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component.Scale9
import io.youi.image.Image
import reactify._

object Scale9Example extends UIExampleScreen with UIScreen {
  override def name: String = "Scale 9"
  override def path: String = "/examples/scale9.html"

  override def createUI(): Unit = {
    Image("/images/scale9.png").foreach { image =>
      container.children += new Scale9(image) {
        size.width := 500.0
        size.height := 500.0
        x1 := 50.0
        x2 := 450.0
        y1 := 50.0
        y2 := 450.0
        position.center := (container.position.center / 2.0)
        position.middle := container.position.middle
      }
      container.children += new Scale9(image) {
        size.width := 250.0
        size.height := 250.0
        x1 := 50.0
        x2 := 450.0
        y1 := 50.0
        y2 := 450.0
        position.center := container.position.center
        position.middle := container.position.middle
      }
      container.children += new Scale9(image) {
        size.width := 650.0
        size.height := 650.0
        x1 := 50.0
        x2 := 450.0
        y1 := 50.0
        y2 := 450.0
        position.center := (container.position.center * 1.5)
        position.middle := container.position.middle
      }
    }
  }
}