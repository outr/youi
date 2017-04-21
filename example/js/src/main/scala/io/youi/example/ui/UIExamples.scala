package io.youi.example.ui

import io.youi.example.ClientExampleApplication
import io.youi.hypertext.Button
import io.youi._
import io.youi.example.ui.hypertext.HTMLScreen
import reactify._

import scala.concurrent.Future

object UIExamples extends HTMLScreen {
  override def name: String = "UI Examples"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    var previous: Option[Button] = None
    ClientExampleApplication.screens.collect {
      case screen: UIExampleScreen => screen
    }.foreach { screen =>
      if (screen != this) {
        val button = new Button {
          text := screen.name
          size.width := 150.0
          size.height.attach { d =>
            scribe.info(s"Height changed for ${screen.name} button to $d")
          }
          val offset: Double = previous.map(_.position.bottom + 45.0).getOrElse(20.0)
          position.top := offset
          position.center := ui.position.center
          event.click.attach { evt =>
            evt.stopPropagation()
            evt.preventDefault()

            ClientExampleApplication.active := screen
          }
        }
        previous = Some(button)
        container.children += button
      }
    }
  }

  override def path: String = "/ui-examples.html"
}