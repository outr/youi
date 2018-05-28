package io.youi.example.ui

import io.youi._
import io.youi.component.VideoView
import io.youi.example.screen.UIExampleScreen
import io.youi.video.Video

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class VideoExample extends UIExampleScreen {
  override def title: String = "Video Example"
  override def path: String = "/examples/video.html"

  override def createUI(): Future[Unit] = Video(History.url().withPath("/sample.mp4"), autoPlay = true, loop = false, muted = false).map { v =>
    container.children += new VideoView {
      video := v
      position.center := container.size.center
      position.middle := container.size.middle
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