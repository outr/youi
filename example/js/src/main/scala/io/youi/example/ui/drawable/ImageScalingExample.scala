package io.youi.example.ui.drawable

import io.youi._
import io.youi.app.screen.DrawableScreen
import io.youi.drawable._
import io.youi.example.ui.UIExampleScreen
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.image.HTMLImage
import io.youi.path.Fill
import io.youi.util.ImageResizer

import scala.concurrent.Future

object ImageScalingExample extends UIExampleScreen with DrawableScreen {
  override def name: String = "Image Scaling"

  override protected val drawable: Future[Drawable] = for {
    font <- OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`)
    image <- HTMLImage(History.url.withPart("/images/colored_lines.jpg"))
    (fast, fastTime) <- timed(image.resize(240.0, 150.0, ImageResizer.Fast))
    (pica, picaTime) <- timed(image.resize(240.0, 150.0, ImageResizer.Pica))
  } yield {
    Group(
      Transformation(25.0, 0.0)(font(s"Fast ($fastTime seconds)", 36.0)),
      Fill(Color.Black),
      Transformation(50.0, 50.0)(fast),
      Transformation(25.0, 200.0)(font(s"Pica ($picaTime seconds)", 36.0)),
      Fill(Color.Black),
      Transformation(50.0, 250.0)(pica)
    )
  }

  private def timed[R](future: Future[R]): Future[(R, Double)] = {
    val start = System.currentTimeMillis()
    future.map(r => (r, (System.currentTimeMillis() - start) / 1000.0))
  }

  override def path: String = "/examples/drawable/image-scaling.html"
}