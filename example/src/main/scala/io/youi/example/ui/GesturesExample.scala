package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.{Container, TextView}
import io.youi.component.support.{BorderSupport, PaddingSupport}
import io.youi.event.{EventSupport, Gestures}
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import spice.net._

class GesturesExample extends UIExampleScreen {
  override def title: String = "Gestures"
  override def path: URLPath = path"/examples/gestures.html"

  override def createUI(): Task[Unit] = Task {
    val status = new TextView {
      content @= "Interact with the box below"
      font.size @= 20.0
      color := ExampleApp.textColor
      position.center := container.size.center
      position.top @= 20.0
    }

    val box = new Container with EventSupport with BorderSupport with PaddingSupport {
      background @= Color.SteelBlue
      size.width @= 250.0
      size.height @= 250.0
      border.radius @= 12.0
      position.center := container.size.center
      position.top @= 80.0
    }

    val label = new TextView {
      content @= "Tap, double-click,<br/>or long-press me"
      font.size @= 18.0
      color @= Color.White
      position.center := box.size.center
      position.middle := box.size.middle
    }
    box.children += label

    val gestures = new Gestures(box, box.event)

    gestures.tap.attach { _ =>
      box.background @= Color.ForestGreen
      status.content @= "Tap detected!"
      label.content @= "Tapped!"
    }

    gestures.doubleClick.attach { _ =>
      box.background @= Color.DarkOrange
      status.content @= "Double-click detected!"
      label.content @= "Double-clicked!"
    }

    gestures.longPress.attach { _ =>
      box.background @= Color.Crimson
      status.content @= "Long-press detected!"
      label.content @= "Long-pressed!"
    }

    container.children ++= Seq(status, box)
  }
}
