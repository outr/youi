package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.BasicText
import io.youi.spatial.Point
import io.youi.task._

import scala.concurrent.duration._

object HelloWorld extends UIExampleScreen with UIScreen {
  override def name: String = "Hello World"
  override def path: String = "/examples/hello.html"

  override def createUI(): Unit = {
    container.children += new BasicText {
      value := "Hello, World!"
      font.size := 48.0
      background := Color.Yellow.withAlpha(0.5)
      fill := Color.Red
      position.center := ui.position.center
      position.middle := ui.position.middle

      ui.renderer.htmlEvents.mouse.move.attach { evt =>
        val local = matrix.world.immutable.inv().localize(Point(evt.clientX, evt.clientY))
//        scribe.info(s"MouseEvent! ${evt.clientX}x${evt.clientY} / $local")
        if (local.x >= 0.0 && local.y >= 0.0 && local.x <= size.width() && local.y <= size.height()) {
          scribe.info("HIT!")
        }
      }
    }
    val text = new BasicText {
      value := "Hello, World!"
      font.size := 56.0
      fill := Color.DarkBlue
      position.center := ui.position.center
      position.middle := ui.position.middle

      forever {
        rotation to 1.0 in 5.seconds andThen(rotation := 0.0)
      }.start(this)
//      rotation := 90.degrees
    }
    container.children += text
  }
}