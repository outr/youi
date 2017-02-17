package io.youi.example.ui

import com.outr.reactify._
import io.youi._
import io.youi.example.screen.ExampleScreen
import io.youi.hypertext.{BasicComponent, Button, Container}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object HelloWorld extends UIExampleScreen {
  override protected def load(): Future[Unit] = super.load().map { _ =>
    container.children += new Button {
      text := "Say 'Hello World'"
      position.center := UI.position.center
      position.middle := UI.position.middle

      click.attach { _ =>
        scribe.info("Hello, World!")
      }
    }
  }

  override def path: String = "/examples/hello.html"
}
