package io.youi.example.ui

import io.youi.VirtualSizeSupport
import io.youi._
import io.youi.hypertext.{ImageView, Label}
import io.youi.hypertext.style.Image

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object VirtualSizeExample extends VirtualSizeSupport with UIExampleScreen {
  override def path: String = "/examples/virtual.html"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    container.children += new ImageView {
      image := Image("/images/1024.jpg")
      position.left := 0.vx
      position.top := 0.vy
      size.width := 1024.vw
      size.height := 768.vh
    }
    container.children += new Label {
      text := "1024x768"
      color := Color.White
      font.family := "sans-serif"
      font.size := 67.vf
      position.left := 45.vx
      position.middle := ui.position.middle
    }
  }
}
