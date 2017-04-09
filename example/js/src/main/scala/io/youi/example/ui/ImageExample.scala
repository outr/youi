package io.youi.example.ui

import reactify._
import io.youi.UI
import io.youi.hypertext.style.Image
import io.youi.hypertext.ImageView

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ImageExample extends UIExampleScreen {
  override def name: String = "Image Example"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val icon = Image("/images/icon.png")
    container.children += new ImageView {    // Top-Left
      image := icon
      position.left := 50.0
      position.top := 50.0
    }
    container.children += new ImageView {    // Top-Right
      image := icon
      position.right := UI.position.right - 50.0
      position.top := 50.0
    }
    container.children += new ImageView {    // Bottom-Left
      image := icon
      position.left := 50.0
      position.bottom := UI.position.bottom - 50.0
    }
    container.children += new ImageView {    // Bottom-Right
      image := icon
      position.right := UI.position.right - 50.0
      position.bottom := UI.position.bottom - 50.0
    }
    container.children += new ImageView {    // Center
      image := icon
      position.center := UI.position.center
      position.middle := UI.position.middle
    }
  }

  override def path: String = "/examples/images.html"
}