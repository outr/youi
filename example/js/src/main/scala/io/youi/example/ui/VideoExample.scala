package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component.Video

object VideoExample extends UIExampleScreen with UIScreen {
  override def name: String = "Video Example"
  override def path: String = "/examples/video.html"

  override def createUI(): Unit = {
    container.children += new Video("/sample.mp4") {
      autoPlay := true
      position.center := renderer.position.center
      position.middle := renderer.position.middle
      event.click.on {
        if (isPaused) {
          play()
        } else {
          pause()
        }
      }
    }
  }
}