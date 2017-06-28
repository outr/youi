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

trait Image extends Drawable {
  val original: Option[Image]
  val width: Double
  val height: Double

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    drawImage(component, context, width, height)
  }

  def drawImage(component: Component, context: CanvasRenderingContext2D, width: Double, height: Double): Unit

  def resized(width: Double, height: Double): Future[Image]

  override lazy val boundingBox: BoundingBox = BoundingBox(0.0, 0.0, width, height)
}

object Image {
  def empty: Image = EmptyImage
  def apply(source: String): Future[Image] = {
    // TODO: support SVG string
    // TODO: support DataURL
    apply(History.url().withPart(source))
  }
  def apply(url: URL,
            width: Option[Double] = None,
            height: Option[Double] = None,
            mode: ImageMode = ImageMode.Quality): Future[Image] = if (url.path.encoded.toLowerCase.endsWith(".svg")) {
    val stream = StreamURL.stream(url)
    stream.map { svgString =>
      val div = dom.create[html.Div]("div")
      div.innerHTML = svgString
      val svg = div.oneByTag[SVGSVGElement]("svg")
      fromSVG(svg, width, height)
    }
  } else {
    val img = dom.create[html.Image]("img")
    val future = fromImage(img, width, height, mode)
    img.src = url.toString
    future
  }
  def apply(file: File): Future[Image] = ???
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

  def fromSVG(svg: SVGSVGElement,
              width: Option[Double] = None,
              height: Option[Double] = None): SVGImage = {
    val original = SVGImage.measure(svg).toSize
    val size = SizeUtility.size(width, height, original)
    SVGImage(svg, size.width, size.height, None)
  }
}