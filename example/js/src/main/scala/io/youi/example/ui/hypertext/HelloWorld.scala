package io.youi.example.ui.hypertext

import io.youi.ui
import io.youi.hypertext.Button

import scala.concurrent.Future

object HelloWorld extends HTMLScreen {
  override def name: String = "HTML Hello World"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    container.children += new Button {
      text := "Say 'Hello World'"
      position.center := ui.position.center
      position.middle := ui.position.middle

      event.click.attach { _ =>
        scribe.info(s"Hello, World! ${position.x()}x${position.y()}, Size: ${size.actual.width()}x${size.actual.height()}")
      }
    }
  }

  override def path: String = "/examples/html/hello.html"
}