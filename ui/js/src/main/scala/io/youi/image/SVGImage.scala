package io.youi.image

import com.outr.{CanvgOptions, canvg}
import io.youi._
import io.youi.dom._
import io.youi.drawable.Context
import io.youi.path.Path
import io.youi.spatial.{BoundingBox, Size}
import io.youi.util.{CanvasPool, LazyFuture}
import org.scalajs.dom.html
import org.scalajs.dom.raw._
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs._

case class SVGImage(private val svg: SVGSVGElement,
                    width: Double,
                    height: Double,
                    measured: Size) extends Image {
  private val context = Val(new Context(CanvasPool(width * ui.ratio, height * ui.ratio), ui.ratio))
  context.changes(new ChangeObserver[Context] {
    override def change(oldValue: Context, newValue: Context): Unit = {
      CanvasPool.restore(oldValue.canvas)     // Cleanup when canvas changes
      reDrawer.flag()                         // Must make sure to redraw on the new canvas
    }
  })
  private val reDrawer = LazyFuture {
    drawAsync(context, 0.0, 0.0, width, height).map { _ =>
      modified := System.currentTimeMillis()
    }
  }

  def modify[R](f: SVGSVGElement => R): Future[R] = {
    val result = f(svg)
    reDrawer.flag().map(_ => result)
  }

  override def drawFast(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = {
    context.drawCanvas(this.context.canvas)(x, y, width, height)
  }

  override def drawAsync(context: Context, x: Double, y: Double, width: Double, height: Double): Future[Unit] = {
    drawToCanvas(context.canvas, x: Double, y: Double, width, height)
  }

  private def drawToCanvas(canvas: html.Canvas, x: Double, y: Double, width: Double, height: Double): Future[Unit] = {
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
      scaleWidth = math.ceil(width * ui.ratio).toInt
      scaleHeight = math.ceil(height * ui.ratio).toInt
      renderCallback = callback
    })
    promise.future
  }

  override def toDataURL: Future[String] = CanvasPool.withCanvasFuture(width, height) { temp =>
    val context = new Context(temp, ui.ratio)
    drawAsync(context, 0.0, 0.0, width, height).map { _ =>
      temp.toDataURL("image/png")
    }
  }

  override def dispose(): Unit = {}

  override def isVector: Boolean = true
}

object SVGImage {
  case class ViewBox(width: Double = 0.0, height: Double = 0.0)

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