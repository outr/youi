package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component.{Image, Texture}

object GesturesExample extends UIExampleScreen with UIScreen {
  override def name: String = "Gestures Example"
  override def path: String = "/examples/gestures.html"

  override def createUI(): Unit = {
    val texture = Texture("/images/icon.png")

    container.children += new Image(texture) {
      position.center := renderer.position.center
      position.middle := renderer.position.middle
      event.pointer.move.attach { evt =>
        scribe.info(s"Move! ${evt.identifier}")
      }
      event.pointer.wheel.attach { evt =>
        scribe.info(s"Wheel: ${evt.delta.x}x${evt.delta.y}x${evt.delta.z}")
      }
      event.gestures.pointers.added.attach { p =>
        scribe.info(s"Added: $p")
      }
      event.gestures.pointers.removed.attach { p =>
        scribe.info(s"Removed: $p")
      }
      event.gestures.tap.attach { p =>
        scribe.info("Tap!")
      }
      event.gestures.click.attach { p =>
        scribe.info("Click!")
      }
      event.gestures.longPress.attach { p =>
        scribe.info("Long press!")
      }
      event.gestures.doubleClick.attach { p =>
        scribe.info("Double click!")
      }
    }
  }
}