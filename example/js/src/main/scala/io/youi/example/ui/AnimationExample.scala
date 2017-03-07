package io.youi.example.ui

import io.youi._
import io.youi.easing.Easing
import io.youi.workflow._
import io.youi.hypertext.{ImageView, Label}
import io.youi.hypertext.style.Image

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object AnimationExample extends UIExampleScreen {
  override def path: String = "/examples/animation.html"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val icon = Image("/images/icon.png")
    val view = new ImageView {
      image := icon
      position.left := 0.0
      position.middle := ui.position.middle
    }
    container.children += view

    var offset = 50.0
    Easing.map.toList.sortBy(_._1).foreach {
      case (name, easingFunction) => {
        val label = new Label {
          text := s"$name Example"
          font.size := 24.0
          font.family := "sans-serif"
          position.top := offset
          position.left := 50.0
          color := Color.DarkSlateBlue

          offset += 25.0
        }

        forever(
          sequential(
            label.position.right to ui.position.right - 50.0 in 5.seconds easing easingFunction,
            sleep(2.seconds),
            label.position.left to 50.0 in 5.seconds easing easingFunction,
            sleep(2.seconds)
          )
        ).start()

        container.children += label
      }
    }

    forever(
      parallel(
        sequential(
          synchronous(view.rotation := 0.0),
          view.rotation to 6.0 in 20.seconds
        ),
        sequential(
          view.position.right to ui.position.right in 5.seconds easing Easing.bounceOut,
          view.position.bottom to ui.position.bottom in 5.seconds easing Easing.bounceOut,
          view.position.left to 0.0 in 5.seconds easing Easing.bounceOut,
          view.position.top to 0.0 in 5.seconds easing Easing.bounceOut
        )
      )
    ).start()
  }
}