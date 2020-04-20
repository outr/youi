package io.youi.example.ui

import io.youi._
import io.youi.font.GoogleFont
import io.youi.component._
import io.youi.component.support.{MeasuredSupport, PositionSupport, ThemeSupport}
import io.youi.component.types.PositionType
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.net._
import reactify._

import scala.concurrent.Future
import scribe.Execution.global

class HelloWorld extends UIExampleScreen {
  override def title: String = "Hello World"
  override def path: Path = path"/examples/hello.html"

  private val text = new TextView() with PositionSupport with MeasuredSupport with EventSupport with ThemeSupport {
    content @= "Hello, World!"
    font.size @= 64.px
    color @= Color.DarkBlue
    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle
  }

  override def createUI(): Future[Unit] = for {
    fnt <- GoogleFont.`Lobster`.load()
  } yield {
    text.font.family @= fnt.family
    container.children += text
  }
}