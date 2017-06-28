package io.youi.image

import com.outr.CanvgImplicits._
import io.youi.component.Component
import io.youi.component.draw.BoundingBox
import io.youi.component.draw.path.Path
import io.youi.dom._
import org.scalajs.dom.raw._

import scala.concurrent.Future

case class SVGImage(svg: SVGSVGElement,
                    width: Double,
                    height: Double,
                    original: Option[Image]) extends Image {
  override def drawImage(component: Component, context: CanvasRenderingContext2D, width: Double, height: Double): Unit = {
    context.drawSvg(svg.outerHTML, 0.0, 0.0, width, height)
  }

  override def resized(width: Double, height: Double): Future[Image] = if (this.width == width && this.height == height) {
    Future.successful(this)
  } else if (original.map(_.width).contains(width) && original.map(_.height).contains(height)) {
    Future.successful(original.get)
  } else {
    val original = this.original.getOrElse(this)
    Future.successful(copy(width = width, height = height, original = Some(original)))
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
      case _: SVGStyleElement => // Nothing to do here
      case _ => scribe.warn(s"Unsupported SVG node: $e.")
    }
    svg.children.foreach(child => measureInternal(child, 0.0, 0.0))
    BoundingBox(minX, minY, maxX, maxY)
  }
}