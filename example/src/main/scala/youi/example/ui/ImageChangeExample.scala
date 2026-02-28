package youi.example.ui

import rapid.Task
import youi.component.ImageView
import youi.event.EventSupport
import youi.example.screen.UIExampleScreen
import youi.image.Image
import spice.net._

class ImageChangeExample extends UIExampleScreen {
  override def title: String = "Image Change"
  override def path: URLPath = path"/examples/image-change.html"

  lazy val imageView: ImageView & EventSupport = new ImageView with EventSupport

  override def createUI(): Task[Unit] = for {
    icon <- Image("/images/icon.png")
    cuteness <- Image("/images/cuteness.jpg")
    tiger <- Image("/images/tiger.svg")
  } yield {
    imageView.image @= tiger
    imageView.position.center := container.size.center()
    imageView.position.middle := container.size.middle()

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