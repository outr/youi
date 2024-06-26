package io.youi.image

import cats.effect.IO
import io.youi._
import io.youi.drawable.{Context, Drawable}
import io.youi.image.resize.ImageResizer
import io.youi.spatial.{BoundingBox, Size}
import io.youi.util.{CanvasPool, FileUtility}
import org.scalajs.dom.{File, html}
import spice.net.URL

trait Image extends Drawable {
  val width: Double
  val height: Double
  lazy val size: Size = Size(width, height)

  override def draw(context: Context, x: Double, y: Double): Unit = draw(context, x, y, width, height)

  def draw(context: Context, x: Double, y: Double, width: Double, height: Double): Unit

  def resize(width: Double, height: Double): IO[Image]

  def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): IO[html.Canvas]

  def clip(x1: Double, y1: Double, x2: Double, y2: Double): IO[Image] = {
    val w = x2 - x1
    val h = y2 - y1
    val dataURL = CanvasPool.withCanvas(w, h) { clipped =>
      val context = new Context(clipped, ratio)
      context.translate(-x1, -y1)
      draw(context, 0.0, 0.0, width, height)
      clipped.toDataURL("image/png")
    }

    Image(dataURL)
  }

  lazy val boundingBox: BoundingBox = BoundingBox(0.0, 0.0, width, height)

  def isVector: Boolean

  def isRaster: Boolean = !isVector

  def toDataURL: IO[String]

  def dispose(): Unit
}

object Image {
  def empty: Image = EmptyImage

  def apply(source: String, width: Double, height: Double): IO[Image] = apply(source).flatMap(_.resize(width, height))

  def apply(source: String): IO[Image] = if (source.indexOf("<svg") != -1) {
    SVGImage(source)
  } else if (source.startsWith("data:image/") || source.startsWith("blob:")) {
    val img = dom.create[html.Image]("img")
    img.src = source
    HTMLImage(img)
  } else {
    apply(History.url.withPart(source))
  }

  def apply(url: URL): IO[Image] = if (url.path.encoded.toLowerCase.endsWith(".svg")) {
    SVGImage(url)
  } else {
    HTMLImage(url)
  }

  def apply(file: File): IO[Image] = file.`type` match {
    case "image/svg+xml" => FileUtility.loadText(file).flatMap { svgString =>
      SVGImage(svgString)
    }
    case _ => FileUtility.loadDataURL(file).flatMap(apply)
  }

  def isImage(file: File): Boolean = file.`type`.startsWith("image/")
}