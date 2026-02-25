package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.{TextView, VideoView}
import io.youi.component.types.Cursor
import io.youi.event.EventSupport
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import io.youi.video.Video
import spice.net._

class VideoExample extends UIExampleScreen {
  override def title: String = "Video"
  override def path: URLPath = path"/examples/video.html"

  override def createUI(): Task[Unit] = Video("/sample.mp4", autoPlay = true, loop = true, muted = true).map { v =>
    val view = new VideoView(v) with EventSupport {
      size.width @= v.width.toDouble
      size.height @= v.height.toDouble
      cursor @= Cursor.Pointer
      position.center := container.size.center
      position.top @= 40.0
    }

    val status = new TextView {
      content @= "Playing (click video to pause)"
      font.size @= 16.0
      color := ExampleApp.textColor
      position.center := container.size.center
      position.top @= 10.0
    }

    view.event.click.on {
      if (v.isPaused) {
        v.play()
        status.content @= "Playing (click video to pause)"
      } else {
        v.pause()
        status.content @= "Paused (click video to play)"
      }
    }

    container.children ++= Seq(status, view)
  }
}
