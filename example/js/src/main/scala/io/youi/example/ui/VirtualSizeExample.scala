package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.{ImageView, TextView}
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.image.Image

import scala.concurrent.Future

object VirtualSizeExample extends UIExampleScreen with UIScreen with VirtualSizeSupport {
  override def name: String = "Virtual Size Example"
  override def path: String = "/examples/virtual.html"

  override def createUI(): Future[Unit] = for {
    img <- Image("/images/1024.jpg")
    fnt <- OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`)
  } yield {
    virtualWidth := 1024.0
    virtualHeight := 768.0

    container.children += new ImageView {
      image := img
      position.left := 0.vx
      position.top := 0.vy
      size.width := 1024.vw
      size.height := 768.vh
    }
    container.children += new TextView {
      value := "1024x768"
      fill := Color.White
      font.file := fnt
      font.size := 66.5.vf
      position.left := 44.vx
      position.middle := container.position.middle
    }
  }
}