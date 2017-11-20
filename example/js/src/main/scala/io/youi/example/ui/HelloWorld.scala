package io.youi.example.ui

import io.youi._
import io.youi.app.screen.{PathActivation, Screen}
import io.youi.component.TextView
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.net.URL
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object HelloWorld extends Screen with PathActivation {
  override protected def title: String = "Hello World"
  override def path: String = "/examples/hello.html"

  val fontURL: URL = GoogleFont.`Open Sans`.`regular`

  override protected def init(): Future[Unit] = super.init().flatMap { _ =>
    OpenTypeFont.fromURL(fontURL).map { fnt =>
      ui.children += new TextView {
        //      cache := true
        //      font.file := fnt
        //      font.size := 48.0
        //      value := "Hello, World!"
        //      fill := Color.DarkBlue
        position.center := ui.position.center
        position.middle := ui.position.middle
      }
    }
  }
}