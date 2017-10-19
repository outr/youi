package io.youi.example.ui.drawable

import io.youi.app.screen.DrawableScreen
import io.youi.drawable._
import io.youi.example.ui.UIExampleScreen

import scala.concurrent.Future

object ImageScalingExample extends UIExampleScreen with DrawableScreen {
  override def name: String = "Image Scaling"

  override protected val drawable: Future[Drawable] = Future.failed(new RuntimeException("Not implemented"))

  override def path: String = "/examples/drawable/image-scaling.html"
}