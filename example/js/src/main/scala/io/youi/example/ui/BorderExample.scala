package io.youi.example.ui

import io.youi._
import io.youi.hypertext.border.BorderStyle
import io.youi.hypertext.{Container, Label}
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object BorderExample extends UIExampleScreen {
  override def name: String = "Border Example"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    container.children += new Container {
      position.center := ui.position.center
      position.middle := ui.position.middle
      size.width := 400.0
      size.height := 300.0
      backgroundColor := Color.DarkCyan
      border.radius := 10.0
      border.color := Some(Color.DarkBlue)
      border.width := Some(5.0)
      border.style := Some(BorderStyle.Dashed)
      border.bottom.right.radius := 0.0
    }

    container.children += new Label {
      color := Color.White
      font.size := 42.0
      font.family := "sans-serif"
      text := "Border Example"

      position.center := ui.position.center
      position.middle := ui.position.middle
    }
  }

  override def path: String = "/examples/border.html"
}
