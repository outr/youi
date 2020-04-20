package io.youi.image

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

class SVGImage private(private val svg: SVGSVGElement, override protected val canvas: html.Canvas, measured: Size) extends CanvasImage {
  private val reDrawer = LazyFuture {
    SVGImage.drawToCanvas(canvas, svg, 0.0, 0.0, canvas.width, canvas.height).map { _ =>
      modified @= System.currentTimeMillis()
    }
  }

  def modify[R](f: SVGSVGElement => R): Future[R] = {
    val result = f(svg)
    reDrawer.flag().map(_ => result)
  }

  override def resize(width: Double, height: Double): Future[SVGImage] = if (this.width == width && this.height == height) {
    Future.successful(this)
  } else {
    SVGImage(svg, width, height)
  }

  override def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): Future[html.Canvas] = {
    SVGImage.drawToCanvas(canvas, svg, 0.0, 0.0, width, height).map(_ => canvas)
  }

  override def isVector: Boolean = true

  def toXML: String = (new XMLSerializer).serializeToString(svg)

  override def toString: String = s"SVGImage($width x $height)"
}

object SVGImage {
  case class ViewBox(width: Double = 0.0, height: Double = 0.0)

  def apply(url: URL): Future[SVGImage] = {
    val stream = StreamURL.stream(url)
    stream.flatMap(apply)
  }

  def apply(svgString: String): Future[SVGImage] = try {
    val div = dom.create[html.Div]("div")
    div.innerHTML = svgString
    val svg = div.oneByTag[SVGSVGElement]("svg")
    apply(svg)
  } catch {
    case t: Throwable => {
      scribe.error(t)
      throw t
    }
  }

  def apply(svg: SVGSVGElement): Future[SVGImage] = {
    val size = measure(svg).toSize
    apply(svg, size.width, size.height)
  }

  def apply(svg: SVGSVGElement, width: Double, height: Double): Future[SVGImage] = {
    val size = measure(svg).toSize
    val canvas = CanvasPool(width, height)
    drawToCanvas(canvas, svg, 0.0, 0.0, width, height).map { _ =>
      new SVGImage(svg, canvas, size)
    }
  }

  def drawToCanvas(canvas: html.Canvas, svg: SVGSVGElement, x: Double, y: Double, width: Double, height: Double): Future[Unit] = {
    val promise = Promise[Unit]
    val callback: js.Function = () => {
      promise.success(())
    }
    canvg(canvas, svg.outerHTML, new CanvgOptions {
      ignoreMouse = true
      ignoreAnimation = true
      ignoreDimensions = true
      ignoreClear = true
      offsetX = math.round(x).toInt
      offsetY = math.round(y).toInt
      scaleWidth = math.ceil(width).toInt
      scaleHeight = math.ceil(height).toInt
      renderCallback = callback
    })
    promise.future
  }

  def measure(svg: SVGSVGElement, applyDimension: Boolean = true, force: Boolean = false): BoundingBox = {
    val viewBox: ViewBox = if (svg.viewBox != null && svg.viewBox.animVal != null) {
      ViewBox(svg.viewBox.animVal.width, svg.viewBox.animVal.height)
    } else {
      ViewBox()
    }
    val definedWidth = if (svg.width.animVal.unitType == SVGLength.SVG_LENGTHTYPE_NUMBER) {
      Some(svg.width.animVal.value)
    } else if (viewBox.width > 0.0) {
      Some(viewBox.width)
    } else {
      None
    }
    val definedHeight = if (svg.height.animVal.unitType == SVGLength.SVG_LENGTHTYPE_NUMBER) {
      Some(svg.height.animVal.value)
    } else if (viewBox.height > 0.0) {
      Some(viewBox.height)
    } else {
      None
    }
    val bb = if (definedWidth.isEmpty || definedHeight.isEmpty || force) {
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
        case e: SVGEllipseElement => {
          minX = math.min(minX, offsetX + (e.cx.baseVal.value - e.rx.baseVal.value))
          minY = math.min(minY, offsetY + (e.cy.baseVal.value - e.ry.baseVal.value))
          maxX = math.max(maxX, offsetX + (e.cx.baseVal.value + e.rx.baseVal.value))
          maxY = math.max(maxY, offsetY + (e.cy.baseVal.value + e.ry.baseVal.value))
        }
        case r: SVGRectElement => {
          minX = math.min(minX, offsetX + r.x.baseVal.value)
          minY = math.min(minY, offsetY + r.y.baseVal.value)
          maxX = math.max(maxX, offsetX + r.x.baseVal.value + r.width.baseVal.value)
          maxY = math.max(maxY, offsetY + r.y.baseVal.value + r.height.baseVal.value)
        }
        case i: SVGImageElement => {
          minX = math.min(minX, offsetX + i.x.baseVal.value)
          minY = math.min(minY, offsetY + i.y.baseVal.value)
          maxX = math.max(maxX, offsetX + i.x.baseVal.value + i.width.baseVal.value)
          maxY = math.max(maxY, offsetY + i.y.baseVal.value + i.height.baseVal.value)
        }
        case g: SVGLinearGradientElement => {
          minX = math.min(minX, offsetX + g.x1.baseVal.value)
          minY = math.min(minY, offsetY + g.y1.baseVal.value)
          maxX = math.max(maxX, offsetX + g.x2.baseVal.value)
          maxY = math.max(maxY, offsetY + g.y2.baseVal.value)
        }
        case p: SVGPolygonElement => {
          (0 until p.points.numberOfItems).foreach { index =>
            val point = p.points.getItem(index)
            minX = math.min(minX, offsetX + point.x)
            minY = math.min(minY, offsetY + point.y)
            maxX = math.max(maxX, offsetX + point.x)
            maxY = math.max(maxY, offsetY + point.y)
          }
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
    } else {
      BoundingBox(0.0, 0.0, definedWidth.get, definedHeight.get)
    }
    if (applyDimension) {
      svg.setAttribute("width", bb.width.toString)
      svg.setAttribute("height", bb.height.toString)
    }
    bb
  }
}