package io.youi.example.ui.hypertext

import io.youi.UI
import io.youi.hypertext.Button

import scala.concurrent.Future

object HelloWorld extends HTMLScreen {
  override def name: String = "HTML Hello World"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    container.children += new Button {
      text := "Say 'Hello World'"
      position.center := UI.position.center
      position.middle := UI.position.middle

      event.click.attach { _ =>
        scribe.info("Hello, World!")
      }
    }
  }

  override def path: String = "/examples/html/hello.html"
}