package io.youi.example.ui

import cats.effect.IO
import io.youi.component.ImageView
import io.youi.component.support.{MeasuredSupport, PositionSupport}
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.image.Image
import spice.net._

class ImageChangeExample extends UIExampleScreen {
  override def title: String = "Image Change"
  override def path: Path = path"/examples/image-change.html"

  lazy val imageView: ImageView with PositionSupport with MeasuredSupport with EventSupport = new ImageView with PositionSupport with MeasuredSupport with EventSupport

  override def createUI(): IO[Unit] = for {
    icon <- Image("/images/icon.png")
    cuteness <- Image("/images/cuteness.jpg")
    tiger <- Image("/images/tiger.svg")
  } yield {
    imageView.image @= tiger
    imageView.position.center := container.size.center
    imageView.position.middle := container.size.middle

    imageView.event.click.on {
      val img = imageView.image()
      if (img == icon) {
        scribe.info("Icon -> Cuteness")
        imageView.image @= cuteness
      } else if (img == cuteness) {
        scribe.info("Cuteness -> Tiger")
        imageView.image @= tiger
      } else if (img == tiger) {
        scribe.info("Tiger -> Icon")
        imageView.image @= icon
      } else {
        scribe.error("No match!")
      }
    }

    container.children += imageView
  }
}