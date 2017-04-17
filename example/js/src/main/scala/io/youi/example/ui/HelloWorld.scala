package io.youi.example.ui

import io.youi.app.screen.UIScreen

object HelloWorld extends UIExampleScreen with UIScreen {
  override def name: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override def createUI(): Unit = {

  }
}
