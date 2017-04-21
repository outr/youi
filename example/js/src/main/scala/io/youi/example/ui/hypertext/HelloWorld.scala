package io.youi.example.ui.hypertext

import io.youi.UI
import io.youi.example.ui.UIExampleScreen
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
        scribe.info(s"Hello, World! ${position.x()}x${position.y()}, Size: ${size.actual.width()}x${size.actual.height()}")
      }
    }
  }

  override def path: String = "/examples/html/hello.html"
}