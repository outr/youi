package io.youi.example.ui.hypertext

import io.youi.UI
import io.youi.example.ui.UIExampleScreen
import io.youi.hypertext.ImageView
import io.youi.hypertext.style.Image

import scala.concurrent.Future

object ImageExample extends HTMLScreen {
  override def name: String = "HTML Image Example"

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

  override def path: String = "/examples/html/images.html"
}
