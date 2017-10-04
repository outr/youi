package io.youi.example.ui.hypertext

import io.youi.hypertext.Label
import io.youi.{AnimationFrame, Color, Display}

import scala.concurrent.Future

object LabelExample extends HTMLScreen {
  override def name: String = "HTML Label Example"

  override def path: String = "/examples/html/labels.html"

  override def load(): Future[Unit] = super.load().map { _ =>
    container.children += new Label {
      text := "Top Left"
      font.family := "sans-serif"
      font.size := 48.0
      color := Color.LightGreen
      position.left := 0.0
      position.top := 0.0
    }

    container.children += new Label {
      text := "Top Right"
      font.family := "sans-serif"
      font.size := 48.0
      color := Color.LightSalmon
      position.right := Display.width
      position.top := 0.0
    }

    container.children += new Label {
      text := "Bottom Left"
      font.family := "sans-serif"
      font.size := 48.0
      color := Color.DarkGoldenRod
      position.left := 0.0
      position.bottom := Display.height
    }

    container.children += new Label {
      text := "Bottom Right"
      font.family := "sans-serif"
      font.size := 48.0
      color := Color.LightBlue
      position.right := Display.width
      position.bottom := Display.height
    }

    container.children += new Label {
      text := "Hello, World!"
      font.family := "sans-serif"
      font.size := 60.0
      color := Color.MidnightBlue
      position.center := Display.center
      position.middle := Display.middle

      rotation := AnimationFrame.timeStamp * 0.001
    }
  }
}
