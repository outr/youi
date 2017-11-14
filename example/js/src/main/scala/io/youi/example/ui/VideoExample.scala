//package io.youi.example.ui
//
//import io.youi._
//import io.youi.app.screen.UIScreen
//import io.youi.component.VideoView
//import io.youi.video.Video
//
//import scala.concurrent.Future
//
//object VideoExample extends UIExampleScreen with UIScreen {
//  override def name: String = "Video Example"
//  override def path: String = "/examples/video.html"
//
//  override def createUI(): Future[Unit] = Video(History.url().withPath("/sample.mp4"), autoPlay = true, loop = false, muted = false).map { v =>
//    container.children += new VideoView {
//      video := v
//      position.center := ui.center
//      position.middle := ui.middle
//      event.click.on {
//        if (isPaused) {
//          play()
//        } else {
//          pause()
//        }
//      }
//    }
//  }
//}