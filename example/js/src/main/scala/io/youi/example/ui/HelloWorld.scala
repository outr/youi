package io.youi.example.ui

import io.youi._
import io.youi.component.extras.HTMLComponent
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.gui._
import io.youi.net._
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class HelloWorld extends UIExampleScreen {
  override def title: String = "Hello World"
  override def path: Path = path"/examples/hello.html"

  override def createUI(): Future[Unit] = GoogleFont.`Lobster`.load().map { fnt =>
    val text = new Text() with PositionSupport {
      content @= "Hello, World!"
      font.family @= fnt.family
      font.size @= 64.px
      color @= Some(Color.DarkBlue)
      position.`type` @= PositionType.Absolute
      position.center := container.size.center
      position.middle := container.size.middle
    }
    HTMLComponent.element(container).appendChild(text)
  }
}