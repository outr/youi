package io.youi.hypertext.style

import io.youi.net.URL
import org.scalajs.dom.{Event, document, html}

import scala.concurrent.{Future, Promise}

sealed trait Image {
  def src: String
}

object EmptyImage extends Image {
  override def src: String = ""
}

case class URLImage(url: URL) extends Image {
  override def src: String = url.encoded.asString
}

case class ExistingImage(img: html.Image) extends Image {
  override def src: String = img.src
}

case class PathImage(path: String) extends Image {
  override def src: String = path
}

object Image {
  def empty: Image = EmptyImage
  def apply(url: URL): URLImage = URLImage(url)
  def apply(img: html.Image): ExistingImage = ExistingImage(img)
  def apply(path: String): PathImage = PathImage(path)

  def reload(url: URL): Future[Unit] = {
    val promise = Promise[Unit]()

    val iFrame = document.createElement("iframe").asInstanceOf[html.IFrame]
    var firstLoad = true
    iFrame.onload = (evt: Event) => {
      if (firstLoad) {
        firstLoad = false
        iFrame.contentWindow.location.reload(true)    // Force reload
      } else {
        promise.success(())
        document.body.removeChild(iFrame)
      }
    }
    iFrame.style.display = "none"
    iFrame.src = url.encoded.asString
    document.body.appendChild(iFrame)

    promise.future
  }
}