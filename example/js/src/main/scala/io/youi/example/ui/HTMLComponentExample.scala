package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.HTMLComponent
import io.youi.hypertext.Button

object HTMLComponentExample extends UIExampleScreen with UIScreen {
  override def name: String = "HTMLComponent Example"
  override def path: String = "/examples/html-component.html"

  override def createUI(): Unit = {
    val button = new Button {
      text := "Hello, World!"
      font.family := "Arial"
      font.size := 48.0
    }
    val component = new HTMLComponent(button) {
      position.center := renderer.position.center
      position.middle := renderer.position.middle
    }
    container.children += component
  }
}
