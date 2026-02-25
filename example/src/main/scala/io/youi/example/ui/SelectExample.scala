package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.SelectList
import io.youi.component.support.FontSupport
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import spice.net._

class SelectExample extends UIExampleScreen {
  override def title: String = "HTMLSelect Example"
  override def path: URLPath = path"/examples/select.html"

  override def createUI(): Task[Unit] = for {
    pacifico <- GoogleFont.`Pacifico`.`regular`.load()
  } yield {
    given (String => String) = identity

    val select = new SelectList[String] with FontSupport {
      list @= List("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
      selected @= Some("Three")
      font.weight @= pacifico
      font.size @= 32.0
      color := ExampleApp.accentColor
      position.center := container.size.center
      position.middle := container.size.middle

      selected.attach {
        case Some(v) => scribe.info(s"Selected: $v")
        case None    =>
      }
    }
    container.children += select
  }
}
