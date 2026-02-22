package io.youi.example.ui

import rapid.Task
import io.youi.component.Component
import io.youi.component.Container
import io.youi.component.support.{ContentSupport, MarginSupport, OverflowSupport}
import io.youi.component.types.{Clear, Overflow}
import io.youi.event.EventSupport
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import spice.net._
import io.youi.{dom, ui}

class UIExamples extends UIExampleScreen {
  override def title: String = "UI Examples"
  override def path: URLPath = path"/ui-examples.html"

  override protected lazy val container: Container & MarginSupport & OverflowSupport = {
    val c = new Container with MarginSupport with OverflowSupport
    c.id @= title
    c
  }

  override def createUI(): Task[Unit] = Task {
    container.overflow.y @= Overflow.Auto

    ExampleApp.screens().collect {
      case screen: UIExampleScreen if screen.title != title => screen
    }.foreach { screen =>
      if (screen != this) {
        val button = new ExampleButton(screen)
        container.children += button
      }
    }
  }

  class ExampleButton(screen: UIExampleScreen) extends Component(dom.create.button) with MarginSupport with EventSupport with ContentSupport {
    content @= screen.title
    size.width @= 250.0
    margin.top @= 10.0
    margin.bottom @= 10.0
    margin.left := ui.size.center() - size.center()

    clear @= Clear.Both

    event.click.attach { evt =>
      evt.preventDefault()
      evt.stopPropagation()

      ExampleApp.active @= screen
    }
  }
}
