package io.youi.image

import cats.effect.IO
import cats.effect.kernel.Deferred
import cats.effect.unsafe.implicits.global
import io.youi.dom
import io.youi.drawable.Context
import io.youi.image.resize.ImageResizer
import io.youi.util.ImageUtility
import org.scalajs.dom.{Event, html}
import spice.net.URL

import scala.scalajs.js

class HTMLImage(private[image] val img: html.Image) extends Image {
  override val width: Double = img.width
  override val height: Double = img.height

  override def draw(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = {
    context.drawImage(img)(x, y, width, height)
  }

  // TODO: make this top-level to Image
  def source: String = img.src

  override def resize(width: Double, height: Double): IO[Image] = resize(width, height, ImageResizer.Smooth)

  def resize(width: Double, height: Double, resizer: ImageResizer): IO[Image] = if (this.width == width && this.height == height) {
    IO.pure(this)
  } else {
    ResizedHTMLImage(this, width, height, resizer)
  }

  override def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): IO[html.Canvas] = {
    ResizedHTMLImage.resizeTo(this, canvas, width, height, resizer)
  }

  override def isVector: Boolean = false

  override def toDataURL: IO[String] = ImageUtility.toDataURL(img)

  override def dispose(): Unit = {}

  override def toString: String = s"HTMLImage($width x $height)"
}

object HTMLImage {
  def apply(url: URL): IO[HTMLImage] = {
    val img = dom.create[html.Image]("img")
    img.src = url.toString
    apply(img)
  }

  def apply(img: html.Image): IO[HTMLImage] = if (img.width > 0 && img.height > 0) {
    IO.pure(new HTMLImage(img))
  } else {
    Deferred[IO, HTMLImage].flatMap { d =>
      var listener: js.Function1[Event, _] = null
      listener = (_: Event) => {
        d.complete(new HTMLImage(img)).map { _ =>
          img.removeEventListener("load", listener)
        }.unsafeRunAndForget()
      }
      img.addEventListener("load", listener)
      d.get
    }
  }
}