package io.youi.image

import io.youi._
import io.youi.dom._
import io.youi.net.URL
import io.youi.spatial.{BoundingBox, Size}
import io.youi.stream.StreamURL
import io.youi.util.{CanvasPool, ImageUtility, SizeUtility}
import org.scalajs.dom._
import org.scalajs.dom.raw.SVGSVGElement

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

trait Image {
  val width: Double
  val height: Double

  def drawFast(context: Context, width: Double, height: Double): Boolean
  def draw(context: Context, width: Double, height: Double): Future[Unit]

  def clip(x1: Double, y1: Double, x2: Double, y2: Double): Future[Image] = {
    val drawable = new ComponentDrawer
    val promise = Promise[String]
    drawable.updateAsync(width, height) { context =>
      draw(context, width, height).map { _ =>
        try {
          val w = x2 - x1
          val h = y2 - y1
          val dataURL = CanvasPool.withCanvas(w, h) { clipped =>
            val ctx = clipped.context
            ctx.drawImage(context.canvas.asInstanceOf[html.Image], x1, y1, w, h, 0.0, 0.0, w, h)
            clipped.toDataURL("image/png")
          }
          promise.success(dataURL)
          ()
        } finally {
          drawable.dispose()
        }
      }
    }
    promise.future.flatMap { dataURL =>
      Image.fromImageSource(dataURL, None, None)
    }
  }

  lazy val boundingBox: BoundingBox = BoundingBox(0.0, 0.0, width, height)

  def isVector: Boolean

  def isRaster: Boolean = !isVector

  def toDataURL: Future[String]

  def dispose(): Unit
}

object Image {
  def empty: Image = EmptyImage
  def apply(source: String,
            width: Option[Double] = None,
            height: Option[Double] = None,
            mode: ImageMode = ImageMode.Quality): Future[Image] = if (source.indexOf("<svg") != -1) {
    fromSVGString(source, width, height)
  } else if (source.startsWith("data:image/")) {
    fromImageSource(source, width, height, mode)
  } else {
    fromURL(History.url.withPart(source), width, height, mode)
  }
  def fromURL(url: URL,
              width: Option[Double] = None,
              height: Option[Double] = None,
              mode: ImageMode = ImageMode.Quality): Future[Image] = if (url.path.encoded.toLowerCase.endsWith(".svg")) {
    val stream = StreamURL.stream(url)
    stream.flatMap(svgString => fromSVGString(svgString, width, height))
  } else {
    fromImageSource(url.toString, width, height, mode)
  }
  def fromFile(file: File,
               width: Option[Double] = None,
               height: Option[Double] = None,
               mode: ImageMode = ImageMode.Quality): Future[Image] = file.`type` match {
    case "image/svg+xml" => ImageUtility.loadText(file).flatMap { svgString =>
      fromSVGString(svgString, width, height)
    }
    case _ => ImageUtility.loadDataURL(file).flatMap { dataURL =>
      fromImageSource(dataURL, width, height, mode)
    }
  }

  def fromImage(img: html.Image,
                width: Option[Double],
                height: Option[Double],
                mode: ImageMode = ImageMode.Quality): Future[TextureImage] = {
    val original = if (img.width > 0 && img.height > 0) {      // Already loaded
      Future.successful(Size(img.width, img.height))
    } else {
      val promise = Promise[Size]
      img.addEventListener("load", (_: Event) => {
        promise.success(Size(img.width, img.height))
      })
      promise.future
    }
    original.map { o =>
      val size = SizeUtility.size(width, height, o)
      TextureImage(img, size.width, size.height, mode)
    }
  }

  def fromImageSource(src: String,
                      width: Option[Double],
                      height: Option[Double],
                      mode: ImageMode = ImageMode.Quality): Future[TextureImage] = {
    val img = dom.create[html.Image]("img")
    val future = fromImage(img, width, height, mode)
    img.src = src
    future
  }

  def fromSVG(svg: SVGSVGElement,
              width: Option[Double] = None,
              height: Option[Double] = None): Future[SVGImage] = {
    val original = SVGImage.measure(svg).toSize
    val size = SizeUtility.size(width, height, original)
    val image = SVGImage(svg, size.width, size.height, original)
    image.drawToCanvas(image.canvas, size.width, size.height).map { _ =>
      image
    }
  }

  def fromSVGString(svgString: String,
                    width: Option[Double] = None,
                    height: Option[Double] = None): Future[SVGImage] = try {
    val div = dom.create[html.Div]("div")
    div.innerHTML = svgString
    val svg = div.oneByTag[SVGSVGElement]("svg")
    fromSVG(svg, width, height)
  } catch {
    case t: Throwable => {
      scribe.error(t)
      throw t
    }
  }
}