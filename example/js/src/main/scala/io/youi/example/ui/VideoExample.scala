//package io.youi.example.ui
//
//import io.youi._
//import io.youi.app.screen.UIScreen
//import io.youi.component.Video
//
//object VideoExample extends UIExampleScreen with UIScreen {
//  override def name: String = "Video Example"
//  override def path: String = "/examples/video.html"
//
//  override def createUI(): Unit = {
//    container.children += new Video("/sample.mp4") {
//      autoPlay := true
//      position.center := ui.position.center
//      position.middle := ui.position.middle
//      background := Color.Black
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