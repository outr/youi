package io.youi.example.ui

import io.youi._
import io.youi.workflow._
import io.youi.app.screen.UIScreen
import io.youi.component.{Image, BasicText, Texture}
import io.youi.easing.Easing

import scala.concurrent.duration._

object AnimationExample extends UIExampleScreen with UIScreen {
  override def name: String = "Animation Example"
  override def path: String = "/examples/animation.html"

  override def createUI(): Unit = {
    val icon = Texture("/images/icon.png")
    val image = new Image(icon) {
      position.left := 0.0
      position.middle := container.position.middle
    }
    container.children += image

    var offset = 50.0
    Easing.map.toList.sortBy(_._1).foreach {
      case (name, easingFunction) => {
        val label = new BasicText {
          value := s"$name Example"
          font.size := 24.0
          font.family := "sans-serif"
          position.top := offset
          position.left := 50.0
          fill := Color.DarkSlateBlue

          offset += 25.0
        }

        forever(
          sequential(
            label.position.right to container.position.right - 50.0 in 5.seconds easing easingFunction,
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
          synchronous(image.rotation := 0.0),
          image.rotation to 6.0 in 20.seconds
        ),
        sequential(
          image.position.right to container.position.right in 5.seconds easing Easing.bounceOut,
          image.position.bottom to container.position.bottom in 5.seconds easing Easing.bounceOut,
          image.position.left to 0.0 in 5.seconds easing Easing.bounceOut,
          image.position.top to 0.0 in 5.seconds easing Easing.bounceOut
        )
      )
    ).start()
  }
}
