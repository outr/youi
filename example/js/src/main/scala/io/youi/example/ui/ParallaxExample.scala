package io.youi.example.ui

import io.youi._
import io.youi.component.HTMLTextView
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.style.{Overflow, Position}
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ParallaxExample extends UIExampleScreen {
  override def title: String = "Parallax Example"
  override def path: String = "/examples/parallax.html"

  override def createUI(): Future[Unit] = GoogleFont.`Lobster`.load().map { fnt =>
    ui.overflow.y := Overflow.Auto
    ui.size.height := 4000.px

    val textView = new HTMLTextView {
      value := s"Hello, World"
      font := fnt
      font.size := {
        val p = ui.position.scroll.percent.y()
        val modifier = 24.0 * ((p - 0.5) * 2.0)
        64.0 + modifier
      }
      color := Color.DarkBlue.withRed(ui.position.scroll.percent.y())
      position.center := container.size.center()
      position.top := header.size.height - math.min(header.size.height, -ui.position.scroll.y())
      position.`type` := Position.Fixed
      position.depth := 100
    }
    container.children += textView
  }
}