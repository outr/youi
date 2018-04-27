package io.youi.example.ui

import io.youi._
import io.youi.component.TextView
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.style.Position
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class HelloWorld extends UIExampleScreen {
  override def title: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override def createUI(): Future[Unit] = GoogleFont.`Lobster`.load().map { fnt =>
    val textView = new TextView {
      value := "Hello, World!"
      font.family := fnt
      font.size := 64.px
      color := Color.DarkBlue
      position.`type` := Position.Absolute
      position.center := container.size.center
      position.middle := container.size.middle
    }
    container.children += textView
  }
}