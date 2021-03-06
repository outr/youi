package io.youi.example.ui

import io.youi._
import io.youi.component.FontAwesomeView
import io.youi.component.support.{MeasuredSupport, PositionSupport}
import io.youi.example.screen.UIExampleScreen
import io.youi.font.FontAwesome
import io.youi.net._
import scribe.Execution.global

import scala.concurrent.Future

class FontAwesomeExample extends UIExampleScreen {
  override def title: String = "Font Awesome Example"
  override def path: Path = path"/examples/font-awesome.html"

  override def createUI(): Future[Unit] = for {
    _ <- FontAwesome.load()
  } yield {
    val iconView = new FontAwesomeView with PositionSupport with MeasuredSupport {
      icon @= FontAwesome.Brands.Android
      font.weight @= "bold"
      font.size @= 128.0
      color @= Color.Blue
      position.center := container.size.center
      position.middle := container.size.middle
    }
    container.children += iconView
  }
}