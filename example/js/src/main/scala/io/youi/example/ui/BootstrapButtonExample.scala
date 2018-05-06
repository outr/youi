package io.youi.example.ui

import io.youi._
import io.youi.component.bootstrap.Button
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class BootstrapButtonExample extends UIExampleScreen {
  override def title: String = "Bootstrap Button"
  override def path: String = "/examples/bootstrap/button.html"

  override def createUI(): Future[Unit] = GoogleFont.`Lobster`.load().map { fnt =>
    val button = new Button {
      value := "Hello, World!"
      position.center := container.size.center
      position.middle := container.size.middle
      font.family := fnt
      font.size := 64.px
      color := Color.White
    }
    container.children += button
  }
}