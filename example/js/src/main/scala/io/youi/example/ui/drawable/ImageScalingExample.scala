package io.youi.example.ui.drawable

import io.youi.Color
import io.youi.app.screen.DrawableScreen
import io.youi.drawable._
import io.youi.example.ui.UIExampleScreen
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.paint.Paint
import io.youi.path.Fill
import io.youi.util.ImageUtility

import scala.concurrent.Future

object ImageScalingExample extends UIExampleScreen with DrawableScreen {
  override def name: String = "Image Scaling"

  override protected val drawable: Future[Drawable] = ImageUtility.

  override def path: String = "/examples/drawable/image-scaling.html"
}