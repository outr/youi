package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component._
import io.youi.drawable.Drawable
import io.youi.image.Image
import io.youi.task._
import io.youi.ui

import scala.concurrent.Future
import scala.concurrent.duration._

object ImageExample extends UIExampleScreen with UIScreen {
  override def name: String = "Image Example"
  override def path: String = "/examples/image.html"

  override protected def drawable: Future[Drawable] = Image("/images/icon.png").map { img =>
    Contained(
      new ImageView {                       // Top-Left
        image := img
        position.left := 50.0
        position.top := 50.0
        size.width := 100.0
        size.height := 100.0
      },
      new ImageView {                       // Top-Right
        image := img
        position.right := ui.width - 50.0
        position.top := 50.0
        opacity := 0.5
      },
      new ImageView {                       // Bottom-Left
        image := img
        position.left := 50.0
        position.bottom := ui.height - 50.0
      },
      new ImageView {                       // Bottom-Right
        image := img
        position.right := ui.width - 50.0
        position.bottom := ui.height - 50.0
        size.width := 300.0
        size.height := 300.0
      },
      new ImageView {                       // Center
        image := img
        position.center := ui.center
        position.middle := ui.middle
        forever {
          rotation to 1.0 in 1.seconds andThen(rotation := 0.0)
        }.start(this)
      }
    )
  }
}