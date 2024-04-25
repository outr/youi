package io.youi.example.ui

import cats.effect.IO
import io.youi._
import io.youi.component._
import io.youi.component.support.{MeasuredSupport, PositionSupport, SingletonThemedComponent}
import io.youi.component.types.PositionType
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import spice.net._
import reactify._

class HelloWorld extends UIExampleScreen {
  override def title: String = "Hello World"
  override def path: URLPath = path"/examples/hello.html"

  override def createUI(): IO[Unit] = for {
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