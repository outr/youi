package io.youi.example.ui

import io.youi._
import io.youi.component._
import io.youi.component.support.{MeasuredSupport, PositionSupport, SingletonThemedComponent}
import io.youi.component.types.PositionType
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.net._
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
    container.children += HelloText
  }

  object HelloText extends TextView with SingletonThemedComponent with MeasuredSupport with PositionSupport {
    content @= "Hello, World!"

    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle

    theme.font.size @= 64.px
    theme.color @= Color.DarkBlue
  }
}