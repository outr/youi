package io.youi.example.ui

import io.youi.Color
import io.youi.example.screen.UIExampleScreen
import io.youi.font.{FontAwesome, FontAwesomeView}
import io.youi.net._
import io.youi.style.FontWeight

import scala.concurrent.Future
import scribe.Execution.global

class FontAwesomeExample extends UIExampleScreen {
  override def title: String = "Font Awesome Example"
  override def path: Path = path"/examples/font-awesome.html"

  override def createUI(): Future[Unit] = for {
    _ <- FontAwesome.load()
  } yield {
    val iconView = new FontAwesomeView {
      value := FontAwesome.Brands.Android
      font.weight := FontWeight("bold")
      font.size := 128.0
      color := Color.Blue
      position.center := container.size.center
      position.middle := container.size.middle
    }
    container.children += iconView
  }
}