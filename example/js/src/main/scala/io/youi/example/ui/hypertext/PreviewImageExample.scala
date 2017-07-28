package io.youi.example.ui.hypertext

import io.youi._
import io.youi.hypertext.style.Image
import io.youi.hypertext.{ImageView, TextInput}
import io.youi.util.ImageUtility
import org.scalajs.dom.Event
import reactify._

import scala.concurrent.Future

object PreviewImageExample extends HTMLScreen {
  override def name: String = "Preview Image"
  override def path: String = "/examples/html/preview-image.html"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val image = new ImageView {
      position.center := ui.position.center
      position.middle := ui.position.middle
    }
    val fileUpload = new TextInput {
      position.center := ui.position.center
      position.top := image.position.bottom + 10.0
      element.`type` = "file"
      element.addEventListener("change", (evt: Event) => {
        if (element.files.length > 0) {
          val file = element.files(0)
          ImageUtility.generatePreview(file, 1024.0, 768.0).foreach {
            case Some(dataURL) => {
              image.image := Image(dataURL)
            }
            case None => scribe.warn(s"Unable to generate preview for ${file.name}.")
          }
        }
      })
    }
    container.children += image
    container.children += fileUpload
  }
}