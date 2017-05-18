package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.{Image, BasicText, Texture}

object VirtualSizeExample extends UIExampleScreen with UIScreen with VirtualSizeSupport {
  override def name: String = "Virtual Size Example"
  override def path: String = "/examples/virtual.html"

  override def createUI(): Unit = {
    virtualWidth := 1024.0
    virtualHeight := 768.0

    val texture = Texture("/images/1024.jpg")
    container.children += new Image(texture) {
      position.left := 0.vx
      position.top := 0.vy
      size.width := 1024.vw
      size.height := 768.vh
    }
    container.children += new BasicText {
      value := "1024x768"
      fill := Color.White
      font.size := 66.5.vf
      position.left := 44.vx
      position.middle := container.position.middle
    }
  }
}
