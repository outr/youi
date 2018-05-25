package io.youi.example.ui

import io.youi._
import io.youi.component.TextView
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.style.Position
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class HelloWorld extends UIExampleScreen {
  override def title: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override def createUI(): Future[Unit] = GoogleFont.`Lobster`.load().map { fnt =>
    val textView = new TextView {
      size.actual.width.attachAndFire { w =>
        scribe.info(s"Text width: $w")
      }
      value := "Hello, World!"
      font.family := fnt
      font.size := 64.px
      color := Color.DarkBlue
      position.`type` := Position.Absolute
      position.center := container.size.actual.center()
      position.middle := container.size.actual.middle()
      position.x.attachAndFire { x =>
        scribe.info(s"Position: $x")
      }
      container.size.center.attachAndFire { c =>
        scribe.info(s"Center: $c, Width: ${container.size.width()}, Actual: ${container.size.actual.width()}")
      }
    }
    container.children += textView
  }
}