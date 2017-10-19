package io.youi.example.ui.drawable

import io.youi.app.screen.DrawableScreen
import io.youi.drawable._
import io.youi.example.ui.UIExampleScreen
import io.youi.image.Image

import scala.concurrent.Future

object ImageScalingExample extends UIExampleScreen with DrawableScreen {
  override def name: String = "Image Scaling"

  override protected val drawable: Future[Drawable] = Image("/images/cuteness.jpg").flatMap { image =>
    image.resize(312.5, 176.0)
  }

  override def path: String = "/examples/drawable/image-scaling.html"
}