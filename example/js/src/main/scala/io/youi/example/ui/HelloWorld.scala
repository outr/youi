package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.BasicText
import io.youi.task._
import scala.concurrent.duration._

object HelloWorld extends UIExampleScreen with UIScreen {
  override def name: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override def createUI(): Unit = {
    container.children += new BasicText {
      value := "Hello, World!"
      font.size := 48.0
      background := Color.Blue
      fill := Color.DarkBlue
      position.center := ui.position.center
      position.middle := ui.position.middle
      size.width := 50.0
      size.height := 50.0
    }
    val text = new BasicText {
      value := "Hello, World!"
      font.size := 48.0
      background := Color.Red
      fill := Color.DarkBlue
      position.center := ui.position.center
      position.middle := ui.position.middle
      size.width := 50.0
      size.height := 50.0

      forever {
        rotation to 1.0 in 5.seconds andThen(rotation := 0.0)
      }.start(this)
    }
    container.children += text
  }
}