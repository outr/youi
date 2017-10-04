package io.youi.example.ui.hypertext

import io.youi.hypertext.Button
import io.youi.ui

import scala.concurrent.Future

object HelloWorld extends HTMLScreen {
  override def name: String = "HTML Hello World"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    container.children += new Button {
      text := "Say 'Hello World'"
      position.center := ui.center
      position.middle := ui.middle

      event.click.attach { _ =>
        scribe.info("Hello, World!")
      }
    }
  }

  override def path: String = "/examples/html/hello.html"
}