package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.TextView
import io.youi.drawable.Drawable
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.net.URL

import scala.concurrent.Future

object HelloWorld extends UIExampleScreen with UIScreen {
  override def name: String = "Hello World"
  override def path: String = "/examples/hello.html"

  val fontURL: URL = GoogleFont.`Open Sans`.`regular`

  override protected def drawable: Future[Drawable] = OpenTypeFont.fromURL(fontURL).map { fnt =>
    new TextView {
      font.file := fnt
      font.size := 48.0
      value := "Hello, World!"
      fill := Color.DarkBlue
      position.center := ui.center
      position.middle := ui.middle
    }
  }
}