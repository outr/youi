//package io.youi.example.ui
//
//import io.youi.component.Scale9
//import io.youi.example.screen.UIExampleScreen
//import io.youi.image.Image
//import spice.net._
//import reactify._
//
//import scala.concurrent.Future
//import scala.concurrent.ExecutionContext.Implicits.global
//
//class Scale9Example extends UIExampleScreen {
//  override def title: String = "Scale 9"
//  override def path: Path = path"/examples/scale9.html"
//
//  override def createUI(): Future[Unit] = Image("/images/scale9.png").map { image =>
//    container.children += new Scale9(image) {
//      size.width @= 500.0
//      size.height @= 500.0
//      x1 @= 50.0
//      x2 @= 450.0
//      y1 @= 50.0
//      y2 @= 450.0
//      position.center := (container.size.center / 2.0)
//      position.middle := container.size.middle
//    }
//    container.children += new Scale9(image) {
//      size.width @= 250.0
//      size.height @= 250.0
//      x1 @= 50.0
//      x2 @= 450.0
//      y1 @= 50.0
//      y2 @= 450.0
//      position.center := container.size.center
//      position.middle := container.size.middle
//    }
//    container.children += new Scale9(image) {
//      size.width @= 650.0
//      size.height @= 650.0
//      x1 @= 50.0
//      x2 @= 450.0
//      y1 @= 50.0
//      y2 @= 450.0
//      position.center := (container.size.center * 1.5)
//      position.middle := container.size.middle
//    }
//  }
//}