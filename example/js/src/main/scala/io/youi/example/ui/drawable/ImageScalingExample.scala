package io.youi.example.ui.drawable

import io.youi._
import io.youi.app.screen.DrawableScreen
import io.youi.drawable._
import io.youi.example.ui.UIExampleScreen
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.image.HTMLImage
import io.youi.image.resize.ImageResizer
import io.youi.path.Fill

import scala.concurrent.Future

object ImageScalingExample extends UIExampleScreen with DrawableScreen {
  override def name: String = "Image Scaling"

  override protected val drawable: Future[Drawable] = for {
    font <- OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`)
    image <- HTMLImage(History.url.withPart("/images/colored_lines.jpg"))
    (fast, fastTime) <- timed(image.resize(240.0, 150.0, ImageResizer.Fast))
    (pica, picaTime) <- timed(image.resize(240.0, 150.0, ImageResizer.Pica))
    (step, stepTime) <- timed(image.resize(240.0, 150.0, ImageResizer.StepDown))
    (smoothLow, smoothLowTime) <- timed(image.resize(240.0, 150.0, ImageResizer.SmoothLow))
    (smoothMedium, smoothMediumTime) <- timed(image.resize(240.0, 150.0, ImageResizer.SmoothMedium))
    (smoothHigh, smoothHighTime) <- timed(image.resize(240.0, 150.0, ImageResizer.SmoothHigh))
  } yield {
    Group(
      Transformation(25.0, 0.0)(font(s"Fast ($fastTime seconds)", 36.0).toDrawable(Color.Black)),
      Transformation(50.0, 50.0)(fast),
      Transformation(25.0, 200.0)(font(s"Pica ($picaTime seconds)", 36.0).toDrawable(Color.Black)),
      Transformation(50.0, 250.0)(pica),
      Transformation(25.0, 400.0)(font(s"Step ($stepTime seconds)", 36.0).toDrawable(Color.Black)),
      Transformation(50.0, 450.0)(step),

      Transformation(375.0, 0.0)(font(s"Smooth Low ($smoothLowTime seconds)", 36.0).toDrawable(Color.Black)),
      Transformation(400.0, 50.0)(smoothLow),
      Transformation(375.0, 200.0)(font(s"Smooth Medium ($smoothMediumTime seconds)", 36.0).toDrawable(Color.Black)),
      Transformation(400.0, 250.0)(smoothMedium),
      Transformation(375.0, 400.0)(font(s"Smooth High ($smoothHighTime seconds)", 36.0).toDrawable(Color.Black)),
      Transformation(400.0, 450.0)(smoothHigh)
    )
  }

  private def timed[R](future: Future[R]): Future[(R, Double)] = {
    val start = System.currentTimeMillis()
    future.map(r => (r, (System.currentTimeMillis() - start) / 1000.0))
  }

  override def path: String = "/examples/drawable/image-scaling.html"
}