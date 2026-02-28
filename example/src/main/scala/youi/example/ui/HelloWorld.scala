package youi.example.ui

import rapid.Task
import youi._
import youi.component._
import youi.component.support.SingletonThemedComponent
import youi.component.types.PositionType
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.font.GoogleFont
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