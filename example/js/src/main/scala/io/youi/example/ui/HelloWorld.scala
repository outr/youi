package io.youi.example.ui

import io.youi._
import io.youi.component._
import io.youi.component.support.{MeasuredSupport, PositionSupport, ThemedComponent}
import io.youi.component.types.PositionType
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.net._
import io.youi.theme.Theme
import reactify._
import scribe.Execution.global

import scala.concurrent.Future

class HelloWorld extends UIExampleScreen {
  override def title: String = "Hello World"
  override def path: Path = path"/examples/hello.html"

  override def createUI(): Future[Unit] = for {
    fnt <- GoogleFont.`Lobster`.load()
  } yield {
    HelloText.font.family @= fnt.family
    container.children += new HelloText
  }

  class HelloText extends TextView with PositionSupport with MeasuredSupport with EventSupport with ThemedComponent {
    override protected def defaultTheme: Theme = HelloText

    content @= "Hello, World!"

    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle
  }

  object HelloText extends Theme {
    font.size @= 64.px
    color @= Color.DarkBlue
  }
}