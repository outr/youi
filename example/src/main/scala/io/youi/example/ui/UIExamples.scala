package io.youi.example.ui

import cats.effect.IO
import io.youi.component.Component
import io.youi.component.support.{MarginSupport, SizeSupport}
import io.youi.event.EventSupport
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import spice.net._
import io.youi.{dom, ui}

class UIExamples extends UIExampleScreen {
  override def title: String = "UI Examples"
  override def path: URLPath = path"/ui-examples.html"

  override def createUI(): IO[Unit] = super.load().map { _ =>
    ExampleApp.screens().collect {
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

      ExampleApp.active @= screen
    }
  }
}