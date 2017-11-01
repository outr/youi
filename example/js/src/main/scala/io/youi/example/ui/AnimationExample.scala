package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.{ImageView, TextView}
import io.youi.easing.Easing
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.image.Image
import io.youi.net.URL
import io.youi.task._

import scala.concurrent.Future
import scala.concurrent.duration._

object AnimationExample extends UIExampleScreen with UIScreen {
  override def name: String = "Animation Example"
  override def path: String = "/examples/animation.html"

  val fontURL: URL = GoogleFont.`Open Sans`.`regular`

  override def createUI(): Future[Unit] = Image("/images/icon.png").map { img =>
    OpenTypeFont.fromURL(fontURL).map { fnt =>
      val image = new ImageView {
        image := img
        position.left := 0.0
        position.middle := container.position.middle
      }
      container.children += image

      var shift = 50.0
      Easing.map.toList.sortBy(_._1).foreach {
        case (name, easingFunction) => {
          val label = new TextView {
            value := s"$name Example"
            cache := true
            font.size := 24.0
            font.file := fnt
            position.top := shift
            position.left := 50.0
            fill := Color.DarkSlateBlue

            shift += 25.0
          }

          forever(
            sequential(
              label.position.right to ui.width - 50.0 in 5.seconds easing easingFunction,
              sleep(2.seconds),
              label.position.left to 50.0 in 5.seconds easing easingFunction,
              sleep(2.seconds)
            )
          ).start(label)

          container.children += label
        }
      }

      forever(
        parallel(
          sequential(
            synchronous(image.rotation := 0.0),
            image.rotation to 6.0 in 20.seconds
          ),
          sequential(
            image.position.right to ui.width in 5.seconds easing Easing.bounceOut,
            image.position.bottom to ui.height in 5.seconds easing Easing.bounceOut,
            image.position.left to 0.0 in 5.seconds easing Easing.bounceOut,
            image.position.top to 0.0 in 5.seconds easing Easing.bounceOut
          )
        )
      ).start(image)
    }
  }
}
