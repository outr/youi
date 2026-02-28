package youi.image

import rapid.Task
import rapid.task.Completable
import youi.dom
import youi.drawable.Context
import youi.image.resize.ImageResizer
import youi.util.ImageUtility
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

  override def resize(width: Double, height: Double): Task[Image] = resize(width, height, ImageResizer.Smooth)

  def resize(width: Double, height: Double, resizer: ImageResizer): Task[Image] = if (this.width == width && this.height == height) {
    Task.pure(this)
  } else {
    ResizedHTMLImage(this, width, height, resizer)
  }

  override def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): Task[html.Canvas] = {
    ResizedHTMLImage.resizeTo(this, canvas, width, height, resizer)
  }

  override def isVector: Boolean = false

  override def toDataURL: Task[String] = ImageUtility.toDataURL(img)

  override def dispose(): Unit = {}

  override def toString: String = s"HTMLImage($width x $height)"
}

object HTMLImage {
  def apply(url: URL): Task[HTMLImage] = {
    val img = dom.create[html.Image]("img")
    img.src = url.toString
    apply(img)
  }

  def apply(img: html.Image): Task[HTMLImage] = if (img.width > 0 && img.height > 0) {
    Task.pure(new HTMLImage(img))
  } else {
    val c: Completable[HTMLImage] = Task.completable[HTMLImage]
    var listener: js.Function1[Event, ?] = null
    listener = (_: Event) => {
      img.removeEventListener("load", listener)
      c.success(new HTMLImage(img))
    }
    img.addEventListener("load", listener)
    c
  }
}
