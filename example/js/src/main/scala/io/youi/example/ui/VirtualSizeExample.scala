package io.youi.example.ui

import io.youi.VirtualSizeSupport
import io.youi._
import io.youi.html.{ImageView, Label}
import io.youi.html.style.Image

object VirtualSizeExample extends VirtualSizeSupport {
  ui.children += new ImageView {
    image := Image("images/1024.jpg")
    position.left := 0.vx
    position.top := 0.vy
    size.width := Size(1024.vw)
    size.height := Size(768.vh)
  }
  ui.children += new Label {
    text := "Virtually Sized"
    position.center := ui.position.center
    position.middle := ui.position.middle
  }
}
