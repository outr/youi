package io.youi.example.ui

import io.youi._
import io.youi.app.screen.{PathActivation, Screen}
import io.youi.component.TextView
import io.youi.component.extras.Position
import io.youi.font.GoogleFont
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object HelloWorld extends Screen with PathActivation {
  override protected def title: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override protected def init(): Future[Unit] = super.init().flatMap { _ =>
    GoogleFont.`Lobster`.load().map { fnt =>
      val textView = new TextView {
        value := "Hello, World!"
        font.family := fnt
        font.size := 64.px
        //      fill := Color.DarkBlue
        position.`type` := Position.Absolute
        position.center := ui.position.center
        position.middle := ui.position.middle
      }
      ui.children += textView
    }
  }
}