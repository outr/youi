package io.youi.image

import io.youi._
import io.youi.component.draw.{BoundingBox, Drawable}
import io.youi.component.{Component, ImageMode}
import io.youi.dom._
import io.youi.net.URL
import io.youi.stream.StreamURL
import io.youi.util.{CanvasPool, ImageUtility, SizeUtility}
import org.scalajs.dom._
import org.scalajs.dom.raw.SVGSVGElement

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

trait Image {
  val original: Option[Image]
  val width: Double
  val height: Double

  def originalWidth: Double = original.map(_.width).getOrElse(width)
  def originalHeight: Double = original.map(_.height).getOrElse(height)

  def drawImage(component: Component,
                canvas: html.Canvas,
                context: CanvasRenderingContext2D,
                width: Double,
                height: Double): Future[Unit]

  def resized(width: Double, height: Double): Future[Image]

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
    Future.successful(fromSVGString(source, width, height))
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
    stream.map(svgString => fromSVGString(svgString, width, height))
  } else {
    fromImageSource(url.toString, width, height, mode)
  }
  def fromFile(file: File,
               width: Option[Double] = None,
               height: Option[Double] = None,
               mode: ImageMode = ImageMode.Quality): Future[Image] = file.`type` match {
    case "image/svg+xml" => ImageUtility.loadText(file).map { svgString =>
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
    original.flatMap { o =>
      val size = SizeUtility.size(width, height, o)
      val canvasFuture = mode match {
        case _ if o.width == size.width && o.height == size.height => Future.successful(None)
        case ImageMode.Speed => Future.successful(None)
        case ImageMode.Quality => {
          val canvas = CanvasPool(size.width, size.height)
          val context = canvas.context
          context.clearRect(0.0, 0.0, size.width, size.height)
          scribe.info(s"Resizing to canvas! Original: ${o.width}x${o.height}, Resized: ${size.width}x${size.height}")
          ImageUtility.resizeToCanvas(img, canvas).map(Some.apply)
        }
      }
      canvasFuture.map { canvas =>
        TextureImage(img, canvas, size.width, size.height, None)
      }
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
              height: Option[Double] = None): SVGImage = {
    val original = SVGImage.measure(svg).toSize
    val size = SizeUtility.size(width, height, original)
    SVGImage(svg, size.width, size.height, None, original)
  }

  def fromSVGString(svgString: String,
                    width: Option[Double] = None,
                    height: Option[Double] = None): SVGImage = try {
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