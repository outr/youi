package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.Text

object HelloWorld extends UIExampleScreen with UIScreen {
  override def name: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override def createUI(): Unit = {
    container.children += new Text {
      value := "Hello, World!"
      font.size := 48.0
      fill := Color.DarkBlue
      position.center := renderer.position.center
      position.middle := renderer.position.middle
    }
  }
}
