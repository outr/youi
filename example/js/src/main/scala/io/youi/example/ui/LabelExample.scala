package io.youi.example.ui

import io.youi._
import io.youi.html.Label

object LabelExample {
  ui.children += new Label {
    text := "Hello, World!"
    position.right := ui.position.right
    position.bottom := ui.position.bottom
  }
}
