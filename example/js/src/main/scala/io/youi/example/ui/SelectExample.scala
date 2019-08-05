package io.youi.example.ui

import io.youi.Color
import io.youi.component.HTMLSelect
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.net._

import scala.concurrent.Future
import scribe.Execution.global

class SelectExample extends UIExampleScreen {
  override def title: String = "HTMLSelect Example"
  override def path: Path = path"/examples/select.html"

  override def createUI(): Future[Unit] = for {
    pacifico <- GoogleFont.`Pacifico`.`regular`.load()
  } yield {
    val select = new HTMLSelect {
      items := Vector("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
      value := "Three"
      font := pacifico
      font.size := 32.0
      color := Color.Green
      position.center := container.size.center
      position.middle := container.size.middle

      value.attach { v =>
        scribe.info(s"Selected: $v")
      }
    }
    container.children += select
  }
}
