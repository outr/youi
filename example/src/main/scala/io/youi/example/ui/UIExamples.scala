package io.youi.example.ui

import io.youi.component.Component
import io.youi.component.support.{MarginSupport, SizeSupport}
import io.youi.event.EventSupport
import io.youi.example.ClientExampleApplication
import io.youi.example.screen.UIExampleScreen
import io.youi.net._
import io.youi.{dom, ui}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UIExamples extends UIExampleScreen {
  override def title: String = "UI Examples"
  override def path: Path = path"/ui-examples.html"

  override def createUI(): Future[Unit] = super.load().map { _ =>
    ClientExampleApplication.screens().collect {
      case screen: UIExampleScreen if screen.title != title => screen
    }.foreach { screen =>
      if (screen != this) {
        val button = new ExampleButton(screen)
        container.children += button
      }
    }
  }

  class ExampleButton(screen: UIExampleScreen) extends Component(dom.create.button) with SizeSupport with MarginSupport with EventSupport {
    element.textContent = screen.title
    size.width @= 250.0
    margin.top @= 10.0
    margin.bottom @= 10.0
    margin.left := ui.size.center - size.center

    element.style.clear = "both"

    event.click.attach { evt =>
      evt.preventDefault()
      evt.stopPropagation()

      ClientExampleApplication.active @= screen
    }
  }
}