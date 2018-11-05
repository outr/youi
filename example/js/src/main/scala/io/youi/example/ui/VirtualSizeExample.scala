package io.youi.example.ui

import io.youi._
import io.youi.component.{HTMLTextView, ImageView}
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.image.Image
import io.youi.net._
import io.youi.virtual.VirtualSizeSupport

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class VirtualSizeExample extends UIExampleScreen with VirtualSizeSupport {
  override def title: String = "Virtual Size Example"
  override def path: Path = path"/examples/virtual.html"

  override def createUI(): Future[Unit] = for {
    img <- Image("/images/1024.jpg")
    fnt <- GoogleFont.`Open Sans`.`regular`.load()
  } yield {
    actual.width := container.size.width
    actual.height := container.size.height
    virtualWidth := 1024.0
    virtualHeight := 768.0

    container.children += new ImageView {
      image := img
      position.left := 0.vx
      position.top := 0.vy
      size.width := 1024.vw
      size.height := 768.vh
    }
    container.children += new HTMLTextView {
      value := "1024x768"
      color := Color.White
      font := fnt
      font.size := 66.vf
      position.left := 42.vx
      position.top := 340.vy
    }
  }
}