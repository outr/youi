package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.Text
import io.youi.style.Theme

object TextExample extends UIExampleScreen with UIScreen {
  override def name: String = "Text Example"
  override def path: String = "/examples/text.html"

  override def createUI(): Unit = {
    val textTheme = new Theme(Text) {
      font.size := 48.0
      fill := Color.Black
    }
    container.children += new Text {
      value := "Top Left"
      theme := textTheme
      fill := Color.LightGreen
      position.left := 0.0
      position.top := 0.0
    }
    container.children += new Text {
      value := "Top Right"
      theme := textTheme
      fill := Color.LightSalmon
      position.right := container.position.right
      position.top := 0.0
    }
    container.children += new Text {
      value := "Bottom Left"
      theme := textTheme
      fill := Color.DarkGoldenRod
      position.left := 0.0
      position.bottom := container.position.bottom
    }
    container.children += new Text {
      value := "Bottom Right"
      theme := textTheme
      fill := Color.LightBlue
      position.right := container.position.right
      position.bottom := container.position.bottom
    }
    container.children += new Text {
      value := "Hello, World!"
      theme := textTheme
      font.size := 60.0
      fill := Color.MidnightBlue
      position.center := container.position.center
      position.middle := container.position.middle
      rotation := AnimationFrame.timeStamp * 0.001
    }
  }
}
