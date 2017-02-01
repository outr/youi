package io.youi.example.ui

import com.outr.reactify._
import com.outr.scribe._
import io.youi.{AnimationFrame, UI}
import io.youi.html.style.Image
import io.youi.html.ImageView

object ImageExample {
  import UI._

  val icon = Image("/images/icon.png")
  children += new ImageView {    // Top-Left
    image := icon
    position.left := 50.0
    position.top := 50.0
  }
  children += new ImageView {    // Top-Right
    image := icon
    position.right := UI.position.right - 50.0
    position.top := 50.0
  }
  children += new ImageView {    // Bottom-Left
    image := icon
    position.left := 50.0
    position.bottom := UI.position.bottom - 50.0
  }
  children += new ImageView {    // Bottom-Right
    image := icon
    position.right := UI.position.right - 50.0
    position.bottom := UI.position.bottom - 50.0
  }
  children += new ImageView {    // Center
    image := icon
    position.center := UI.position.center
    position.middle := UI.position.middle
  }
}
