package io.youi.component.draw

import com.outr.CanvgImplicits._
import io.youi.component.Component
import io.youi.component.draw.path.Path
import io.youi.{History, dom}
import io.youi.dom._
import io.youi.net.URL
import io.youi.stream.StreamURL
import org.scalajs.dom.html
import org.scalajs.dom.raw._
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SVGDrawable(val svg: SVGSVGElement, x: Double, y: Double) extends Drawable {
  private val _measured = Var[BoundingBox](BoundingBox.zero)
  val measured: Val[BoundingBox] = _measured.asVal

  measure()

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    context.drawSvg(svg.outerHTML, x, y, width, height)
  }

  def width: Double = svg.width.animVal.unitType match {
    case SVGLength.SVG_LENGTHTYPE_PERCENTAGE => measured.width
    case SVGLength.SVG_LENGTHTYPE_NUMBER => svg.width.animVal.value
  }
  def height: Double = svg.height.animVal.unitType match {
    case SVGLength.SVG_LENGTHTYPE_PERCENTAGE => measured.height
    case SVGLength.SVG_LENGTHTYPE_NUMBER => svg.height.animVal.value
  }

  override def boundingBox: BoundingBox = if (svg.width.animVal.unitType == SVGLength.SVG_LENGTHTYPE_PERCENTAGE
                                           || svg.height.animVal.unitType == SVGLength.SVG_LENGTHTYPE_PERCENTAGE) {
    measured()
  } else {
    BoundingBox(x, y, x + width, y + height)
  }

  def measure(): BoundingBox = {
    val measured = SVGDrawable.measure(svg)
    val adjusted = measured.shift(x, y)
    _measured := adjusted
    adjusted
  }
}

object SVGDrawable {
  def apply(svg: String, x: Double = 0.0, y: Double = 0.0): SVGDrawable = {
    val div = dom.create[html.Div]("div")
    div.innerHTML = svg
    val tag = div.oneByTag[SVGSVGElement]("svg")
    new SVGDrawable(tag, x, y)
  }

  def fromURL(url: URL, x: Double = 0.0, y: Double = 0.0): Future[SVGDrawable] = StreamURL.stream(url).map { svg =>
    apply(svg, x, y)
  }

  def fromPath(path: String, x: Double = 0.0, y: Double = 0.0): Future[SVGDrawable] = {
    fromURL(History.url().withPath(path), x, y)
  }

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