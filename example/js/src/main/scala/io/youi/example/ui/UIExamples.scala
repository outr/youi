package io.youi.example.ui

import io.youi.Color
import io.youi.example.ClientExampleApplication
import io.youi.component.bootstrap.Button
import io.youi.example.screen.UIExampleScreen
import io.youi.net._
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UIExamples extends UIExampleScreen {
  override def title: String = "UI Examples"
  override def path: Path = path"/ui-examples.html"

  override def createUI(): Future[Unit] = super.load().map { _ =>
    var previous: Option[Button] = None
    ClientExampleApplication.screens().collect {
      case screen: UIExampleScreen if screen.title != title => screen
    }.foreach { screen =>
      if (screen != this) {
        val button: Button = new Button {
          value := screen.title
          color := Color.White
          size.width := 250.0
          val offset: Double = previous.map(_.position.bottom + 45.0).getOrElse(20.0)
          position.top := offset
          position.center := container.size.center
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
}