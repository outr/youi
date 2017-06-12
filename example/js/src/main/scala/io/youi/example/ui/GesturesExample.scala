package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component.{Image, Texture}
import io.youi.task._

import scala.concurrent.duration._

object GesturesExample extends UIExampleScreen with UIScreen {
  override def name: String = "Gestures Example"
  override def path: String = "/examples/gestures.html"

  override def createUI(): Unit = {
    val texture = Texture("/images/icon.png")

    container.children += new Image(texture) {
      position.center := renderer.position.center
      position.middle := renderer.position.middle
      event.gestures.pointers.attach { events =>
        scribe.info(s"Events: ${events.keys.mkString(", ")}")
      }
    }
  }
}