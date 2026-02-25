package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component._
import io.youi.component.support.SingletonThemedComponent
import io.youi.component.types.PositionType
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import spice.net._
import reactify._

class HelloWorld extends UIExampleScreen {
  override def title: String = "Hello World"
  override def path: URLPath = path"/examples/hello.html"

  override def createUI(): Task[Unit] = for {
    fnt <- GoogleFont.`Lobster`.load()
  } yield {
    container.children += new TextView {
      font.family @= fnt.family
      font.size @= 64.px

      color := ExampleApp.accentColor
      
      position.center := container.size.center
      position.middle := container.size.middle

      content @= "Hello, World!"
    }
  }
}