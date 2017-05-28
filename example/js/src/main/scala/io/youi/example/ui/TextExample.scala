package io.youi.example.ui

import io.youi.Color
import io.youi.app.screen.UIScreen
import io.youi.component.Text
import io.youi.component.font.Font
import io.youi.style.Paint

object TextExample extends UIExampleScreen with UIScreen {
  override def name: String = "Text Example"
  override def path: String = "/examples/text.html"

  override def createUI(): Unit = {
    container.children += new Text {
      value := "Hello, World!"
      font.file := Font.fromPath("/fonts/Pacifico.ttf")
      font.size := 96.0
      fill := Paint.horizontal(Color.Red, Color.Green, Color.Blue)
      position.center := container.position.center
      position.middle := container.position.middle
    }
    container.children += new Text {
      value := "Hello, World!"
      font.file := Font.fromPath("/fonts/Pacifico.ttf")
      font.size := 96.0
      Paint.image("/cuteness.jpg").foreach { paint =>
        fill := paint
      }
      position.center := container.position.center
      position.middle := container.position.middle - 100.0

      event.click.attach { mouseEvent =>
        val tp = textPaths.get
        val touching = tp.touching(mouseEvent.x, mouseEvent.y)
        scribe.info(s"Checking: ${mouseEvent.x}x${mouseEvent.y}, touching: ${touching.map(_.char)}")
      }
    }
  }
}
