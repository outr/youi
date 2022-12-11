//package io.youi.example.ui.drawable
//
//import io.youi.app.screen.DrawableScreen
//import io.youi.drawable._
//import io.youi.example.ui.UIExampleScreen
//import io.youi.image.{AnimatedImage, Image}
//
//import scala.concurrent.Future
//
//object AnimatedImageExample extends UIExampleScreen with DrawableScreen {
//  override def name: String = "AnimatedImage Example"
//
//  override protected val drawable: Future[Drawable] = {
//    Future.sequence((0 until 25).map(index => s"/images/scumbag_panda_$index.png").toVector.map(Image.apply)).map { frames =>
//      new AnimatedImage(frames, 0.1)
//    }
//  }
//
//  override def path: String = "/examples/drawable/animated-image.html"
//}