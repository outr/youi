package io.youi.image

import io.youi.dom
import io.youi.drawable.Context
import io.youi.image.resize.ImageResizer
import io.youi.net.URL
import io.youi.util.ImageUtility
import org.scalajs.dom.{Event, html}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs.js

class HTMLImage(private[image] val img: html.Image) extends Image {
  override val width: Double = img.width
  override val height: Double = img.height

  override def draw(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = {
    context.drawImage(img)(x, y, width, height)
  }

  // TODO: make this top-level to Image
  def source: String = img.src

  override def resize(width: Double, height: Double): Future[Image] = resize(width, height, ImageResizer.Smooth)

  def resize(width: Double, height: Double, resizer: ImageResizer): Future[Image] = if (this.width == width && this.height == height) {
    Future.successful(this)
  } else {
    ResizedHTMLImage(this, width, height, resizer)
  }

  override def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): Future[html.Canvas] = {
    ResizedHTMLImage.resizeTo(this, canvas, width, height, resizer)
  }

  override def isVector: Boolean = false

  override def toDataURL: Future[String] = ImageUtility.toDataURL(img)

  override def dispose(): Unit = {}

  override def toString: String = s"HTMLImage($width x $height)"
}

object HTMLImage {
  def apply(url: URL): Future[HTMLImage] = {
    val img = dom.create[html.Image]("img")
    img.src = url.toString
    apply(img)
  }

  def apply(img: html.Image): Future[HTMLImage] = if (img.width > 0 && img.height > 0) {
    Future.successful(new HTMLImage(img))
  } else {
    val promise = Promise[HTMLImage]()
    val listener: js.Function1[Event, _] = (_: Event) => {
      promise.success(new HTMLImage(img))
    }
    img.addEventListener("load", listener)
    val future = promise.future
    future.onComplete(_ => img.removeEventListener("load", listener))
    future
  }
}