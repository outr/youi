package io.youi.example.ui

import io.youi._
import io.youi.component.{HTMLTextView, ImageView}
import io.youi.easing.Easing
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.image.Image
import io.youi.net._
import io.youi.style.Position
import io.youi.task._

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class AnimationExample extends UIExampleScreen {
  override def title: String = "Animation Example"
  override def path: Path = path"/examples/animation.html"

  override def createUI(): Future[Unit] = Image("/images/icon.png").flatMap { img =>
    GoogleFont.`Open Sans`.`regular`.load().map { fnt =>
      val image = new ImageView {
        image := img
        position.`type` := Position.Absolute
        position.left := 0.0
        position.middle := container.size.middle
      }

      var shift = 50.0
      Easing.map.toList.sortBy(_._1).foreach {
        case (name, easingFunction) => {
          val label = new HTMLTextView {
            value := s"$name Example"
            font.size := 24.0
            font := fnt
            position.`type` := Position.Absolute
            position.top := shift
            position.left := 50.0
            color := Color.DarkSlateBlue

            shift += 25.0
          }

          forever(
            sequential(
              label.position.right to ui.size.width - 50.0 in 5.seconds easing easingFunction,
              sleep(2.seconds),
              label.position.left to 50.0 in 5.seconds easing easingFunction,
              sleep(2.seconds)
            )
          ).start(label)

          container.children += label
        }
      }

      container.children += image

      forever(
        parallel(
          sequential(
            synchronous(image.rotation := 0.0),
            image.rotation to 6.0 in 20.seconds
          ),
          sequential(
            image.position.right to container.size.width in 5.seconds easing Easing.bounceOut,
            image.position.bottom to container.size.height in 5.seconds easing Easing.bounceOut,
            image.position.left to 0.0 in 5.seconds easing Easing.bounceOut,
            image.position.top to 0.0 in 5.seconds easing Easing.bounceOut
          )
        )
      ).start(image)
    }
  }
}