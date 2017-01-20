package io.youi.example.ui

import io.youi.VirtualSizeSupport
import io.youi._
import io.youi.html.{ImageView, Label}
import io.youi.html.style.{Color, Image}

object VirtualSizeExample extends VirtualSizeSupport {
  ui.children += new ImageView {
    image := Image("images/1024.jpg")
    position.left := 0.vx
    position.top := 0.vy
    size.width := Size(1024.vw)
    size.height := Size(768.vh)
  }
  ui.children += new Label {
    text := "1024x768"
    color := Color.White
    font.family := "sans-serif"
    font.size := Size(67.vf)
    position.left := 45.vx
    position.middle := ui.position.middle
  }
}
