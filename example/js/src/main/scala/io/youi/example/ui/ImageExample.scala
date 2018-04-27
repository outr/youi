package io.youi.example.ui

import io.youi.app.screen.{PathActivation, Screen, UIScreen}
import io.youi.component._
import io.youi.example.screen.UIExampleScreen
import io.youi.image.Image
import io.youi.style.Position
import io.youi.task._
import io.youi.ui

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class ImageExample extends UIExampleScreen {
  override def title: String = "Image Example"
  override def path: String = "/examples/image.html"

  override def createUI(): Future[Unit] = Image("/images/icon.png").map { img =>
    container.children += Container(
      new ImageView {                       // Top-Left
        image := img
        position.`type` := Position.Absolute
        position.left := 50.0
        position.top := 50.0
        size.width := 100.0
        size.height := 100.0
      },
      new ImageView {                       // Top-Right
        image := img
        position.`type` := Position.Absolute
        position.right := container.size.width - 50.0
        position.top := 50.0
        opacity := 0.5
      },
      new ImageView {                       // Bottom-Left
        image := img
        position.`type` := Position.Absolute
        position.left := 50.0
        position.bottom := container.size.height - 50.0
      },
      new ImageView {                       // Bottom-Right
        image := img
        position.`type` := Position.Absolute
        position.right := container.size.width - 50.0
        position.bottom := container.size.height - 50.0
        size.width := 300.0
        size.height := 300.0
      },
      new ImageView {                       // Center
        image := img
        position.`type` := Position.Absolute
        position.center := container.size.center
        position.middle := container.size.middle
        forever {
          rotation to 1.0 in 1.seconds andThen(rotation := 0.0)
        }.start(this)
      }
    )
  }
}