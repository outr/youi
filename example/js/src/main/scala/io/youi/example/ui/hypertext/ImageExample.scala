package io.youi.example.ui.hypertext

import io.youi.Display
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
      position.right := Display.width - 50.0
      position.top := 50.0
    }
    container.children += new ImageView {    // Bottom-Left
      image := icon
      position.left := 50.0
      position.bottom := Display.height - 50.0
    }
    container.children += new ImageView {    // Bottom-Right
      image := icon
      position.right := Display.width - 50.0
      position.bottom := Display.height - 50.0
    }
    container.children += new ImageView {    // Center
      image := icon
      position.center := Display.center
      position.middle := Display.middle
    }
  }

  override def path: String = "/examples/html/images.html"
}
