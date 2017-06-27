package io.youi.component

import io.youi.component.draw.{BoundingBox, Drawable}
import org.scalajs.dom._
import org.scalajs.dom.raw._

import scala.concurrent.{Future, Promise}
import com.outr.CanvgImplicits._
import io.youi.Size
import io.youi.component.draw.path.Path
import io.youi._
import io.youi.dom._
import io.youi.util.{CanvasPool, ImageUtility, SizeUtility}
import scala.concurrent.ExecutionContext.Implicits.global

trait Image extends Drawable {
  val original: Option[Image]
  val width: Double
  val height: Double

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    drawImage(component, context, width, height)
  }

  def drawImage(component: Component, context: CanvasRenderingContext2D, width: Double, height: Double): Unit

  override lazy val boundingBox: BoundingBox = BoundingBox(0.0, 0.0, width, height)
}

object Image {
  def apply(source: String): Future[Image] = ???
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
        case _ if width.contains(size.width) && height.contains(size.height) => Future.successful(None)
        case ImageMode.Speed => Future.successful(None)
        case ImageMode.Quality => {
          val canvas = CanvasPool(size.width, size.height)
          val context = canvas.context
          context.clearRect(0.0, 0.0, size.width, size.height)
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

case class SVGImage(svg: SVGSVGElement,
                    width: Double,
                    height: Double,
                    original: Option[Image]) extends Image {
  override def drawImage(component: Component, context: CanvasRenderingContext2D, width: Double, height: Double): Unit = {
    context.drawSvg(svg.outerHTML, 0.0, 0.0, width, height)
  }
}

object SVGImage {
  def measure(svg: SVGElement): BoundingBox = {
    var minX = 0.0
    var minY = 0.0
    var maxX = 0.0
    var maxY = 0.0
    def measureInternal(e: Element, offsetX: Double, offsetY: Double): Unit = e match {
      case g: SVGGElement => {
        var ox = offsetX
        var oy = offsetY
        (0 until g.transform.baseVal.numberOfItems).foreach { index =>
          val transform = g.transform.baseVal.getItem(index)
          if (transform.`type` == SVGTransform.SVG_TRANSFORM_TRANSLATE) {
            ox += transform.matrix.e
            oy += transform.matrix.f
          }
        }
        g.children.foreach(child => measureInternal(child, ox, oy))
      }
      case c: SVGCircleElement => {
        minX = math.min(minX, offsetX + (c.cx.baseVal.value - c.r.baseVal.value))
        minY = math.min(minY, offsetY + (c.cy.baseVal.value - c.r.baseVal.value))
        maxX = math.max(maxX, offsetX + (c.cx.baseVal.value + c.r.baseVal.value))
        maxY = math.max(maxY, offsetY + (c.cy.baseVal.value + c.r.baseVal.value))
      }
      case p: SVGPathElement => {
        val path = Path(p.getAttribute("d"))
        minX = math.min(minX, offsetX + path.boundingBox.x1)
        minY = math.min(minY, offsetY + path.boundingBox.y1)
        maxX = math.max(maxX, offsetX + path.boundingBox.x2)
        maxY = math.max(maxY, offsetY + path.boundingBox.y2)
      }
      case _ => scribe.warn(s"Unsupported SVG node: $e.")
    }
    svg.children.foreach(child => measureInternal(child, 0.0, 0.0))
    BoundingBox(minX, minY, maxX, maxY)
  }
}

case class TextureImage(img: html.Image,
                        canvas: Option[html.Canvas],
                        width: Double,
                        height: Double,
                        original: Option[Image]) extends Image {
  override def drawImage(component: Component, context: CanvasRenderingContext2D, width: Double, height: Double): Unit = {
    context.drawImage(canvas.getOrElse(img).asInstanceOf[html.Image], 0.0, 0.0, width, height)
  }
}