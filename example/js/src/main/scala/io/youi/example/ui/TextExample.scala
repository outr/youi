package io.youi.example.ui

import io.youi.Color
import io.youi.app.screen.UIScreen
import io.youi.component.Text
import io.youi.font.Font
import io.youi.paint.{Paint, Stroke}
import reactify._

object TextExample extends UIExampleScreen with UIScreen {
  override def name: String = "Text Example"
  override def path: String = "/examples/text.html"

  override def createUI(): Unit = {
    container.children += new Text {
      value := "Hello, World!"
      font.file := Font.fromPath("/fonts/Pacifico.ttf")
      font.size := 96.0
      fill := Paint.horizontal(this).distributeColors(Color.Red, Color.Green, Color.Blue)
      position.center := container.position.center
      position.middle := container.position.middle
    }
    container.children += new Text {
      value := "Hello, World!"
      font.file := Font.fromPath("/fonts/Roboto-Black.ttf")
      font.size := 96.0
      Paint.image("/images/cuteness.jpg").foreach { paint =>
        fill := paint
      }
      stroke := Stroke(Color.Black, lineWidth = 0.5)
      position.center := container.position.center
      position.middle := container.position.middle - 100.0
    }
    container.children += new Text {
      value := "Hello, World!"
      font.file := Font.fromPath("/fonts/Chivo-Black.ttf")
      font.size := 96.0
      Paint.video("/sample.mp4").foreach { paint =>
        fill := paint
      }
      stroke := Stroke(Color.Black, lineWidth = 0.5)
      position.center := container.position.center
      position.middle := container.position.middle + 100.0

      // TODO: support auto-pause / resume of video paint

      override def update(delta: Double): Unit = {
        if (globalVisibility()) {
          reDraw.flag()
        }
        super.update(delta)
      }
    }
  }
}
