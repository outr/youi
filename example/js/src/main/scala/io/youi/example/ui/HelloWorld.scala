package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.BasicText
import org.scalajs.dom._

object HelloWorld extends UIExampleScreen with UIScreen {
  override def name: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override def createUI(): Unit = {
    val text = new BasicText {
      value := "Hello, World!"
      font.size := 48.0
      background := Color.Red
      scribe.info(s"Background set to red on $this...")
      fill := Color.DarkBlue
      position.center := ui.position.center
      position.middle := ui.position.middle
      size.width := 50.0
      size.height := 50.0
//      document.body.appendChild(drawable.canvas)
    }
    container.children += text
  }
}