//package io.youi.example.ui
//
//import io.youi.app.screen.UIScreen
//import io.youi.component._
//import io.youi.task._
//
//import scala.concurrent.duration._
//
//object ImageExample extends UIExampleScreen with UIScreen {
//  override def name: String = "Image Example"
//  override def path: String = "/examples/image.html"
//
//  override def createUI(): Unit = {
//    val icon = "/images/icon.png"
//
//    container.children += new ImageView(icon) {     // Top-Left
//      position.left := 50.0
//      position.top := 50.0
//      size.width := 100.0
//      size.height := 100.0
//    }
//    container.children += new ImageView(icon) {     // Top-Right
//      position.right := renderer.position.right - 50.0
//      position.top := 50.0
//    }
//    container.children += new ImageView(icon) {     // Bottom-Left
//      position.left := 50.0
//      position.bottom := renderer.position.bottom - 50.0
//    }
//    container.children += new ImageView(icon) {     // Bottom-Right
//      position.right := renderer.position.right - 50.0
//      position.bottom := renderer.position.bottom - 50.0
//      size.width := 300.0
//      size.height := 300.0
//    }
//    container.children += new ImageView(icon) {     // Center
//      position.center := renderer.position.center
//      position.middle := renderer.position.middle
//
//      forever {
//        rotation to 1.0 in 1.seconds andThen(rotation := 0.0)
//      }.start(this)
//    }
//  }
//}