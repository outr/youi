package io.youi.example.ui

import io.youi._
import io.youi.easing.Easing
import io.youi.workflow._
import io.youi.hypertext.ImageView
import io.youi.hypertext.style.Image

import scala.concurrent.duration._

object AnimationExample {
  val icon = Image("/images/icon.png")
  val view =  new ImageView {
    image := icon
    position.left := 0.0
    position.middle := ui.position.middle
  }
  ui.children += view

  forever(
    sequential(
      view.position.right to ui.position.right in 5.seconds easing Easing.bounceOut,
      view.position.left to 0.0 in 5.seconds easing Easing.bounceOut
    )
  ).start()
}
