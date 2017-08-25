package io.youi

import io.youi.component.Component
import io.youi.paint._
import io.youi.spatial.Matrix3
import org.scalajs.dom.{CanvasRenderingContext2D, html}

import scala.scalajs.js
import org.scalajs.dom._

class Context(val canvas: html.Canvas) {
  lazy val canvasContext = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def width: Double = canvas.width.toDouble
  def height: Double = canvas.height.toDouble

  def transform(matrix: Matrix3): Unit = {
    canvasContext.setTransform(matrix.m00, matrix.m01, matrix.m10, matrix.m11, matrix.m02, matrix.m12)
  }

  def save(): Unit = canvasContext.save()
  def restore(): Unit = canvasContext.restore()

  def state[R](f: => R): R = try {
    save()
    f
  } finally {
    restore()
  }

  def transform(component: Component): Unit = {
    transform(Matrix3.Identity)
    canvasContext.translate(component.position.x(), component.position.y())
    canvasContext.translate(component.pivot.x(), component.pivot.y())
    canvasContext.rotate(component.rotation() * (math.Pi * 2.0))
    canvasContext.translate(-component.pivot.x(), -component.pivot.y())
  }

  def translate(x: Double, y: Double): Unit = canvasContext.translate(x, y)

  def draw(component: Component): Unit = {
    val canvas = component.drawer.canvas
    canvasContext.drawImage(canvas.asInstanceOf[html.Image], 0.0, 0.0, canvas.width, canvas.height)
  }

  def drawImage(image: html.Image)
               (x: Double = 0.0,
                y: Double = 0.0,
                width: Double = image.width,
                height: Double = image.height): Unit = {
    canvasContext.drawImage(image, x, y, width, height)
  }

  def drawCanvas(canvas: html.Canvas)
                (x: Double = 0.0,
                 y: Double = 0.0,
                 width: Double = canvas.width,
                 height: Double = canvas.height): Unit = {
    canvasContext.drawImage(canvas.asInstanceOf[html.Image], x, y, width, height)
  }

  def drawVideo(video: html.Video)
               (x: Double = 0.0,
                y: Double = 0.0,
                width: Double = video.width,
                height: Double = video.height): Unit = {
    canvasContext.drawImage(video.asInstanceOf[html.Image], x, y, width, height)
  }

  def rect(x: Double, y: Double, width: Double, height: Double): Unit = {
    canvasContext.rect(x, y, width, height)
  }

  def fill(paint: Paint, apply: Boolean): Unit = if (paint.nonEmpty) {
    canvasContext.fillStyle = paint2Any(paint)
    if (apply) canvasContext.fill()
  }

  def stroke(stroke: Stroke, apply: Boolean): Unit = if (stroke.nonEmpty) {
    canvasContext.strokeStyle = paint2Any(stroke.paint)
    canvasContext.lineWidth = stroke.lineWidth
    canvasContext.setLineDash(js.Array(stroke.lineDash: _*))
    canvasContext.lineDashOffset = stroke.lineDashOffset
    canvasContext.lineCap = stroke.lineCap.value
    canvasContext.lineJoin = stroke.lineJoin.value
    if (apply) canvasContext.stroke()
  }

  def setShadow(blur: Double, color: Color, x: Double, y: Double): Unit = {
    canvasContext.shadowBlur = blur
    canvasContext.shadowColor = color.toRGBA
    canvasContext.shadowOffsetX = x
    canvasContext.shadowOffsetY = y
  }

  def setFont(family: String, size: Double, style: String, variant: String, weight: String): Unit = {
    canvasContext.font = s"$style $variant $weight ${size}px $family"
  }

  def lineJoin(value: String): Unit = canvasContext.lineJoin = value
  def miterLimit(value: Double): Unit = canvasContext.miterLimit = value
  def textBaseline(value: String): Unit = canvasContext.textBaseline = value

  def measureText(text: String, size: Size = Size.zero): Size = {
    val div = dom.create[html.Div]("div")
    div.style.font = canvasContext.font
    div.style.display = "inline-block"
    div.innerHTML = text
    document.body.appendChild(div)
    val width = div.clientWidth
    val height = div.clientHeight
    document.body.removeChild(div)
    size.set(width, height)
  }

  def fillText(text: String, x: Double = 0.0, y: Double = 0.0, maxWidth: Double = 10000.0): Unit = {
    canvasContext.textBaseline = "top"
    canvasContext.fillText(text, x, y, maxWidth)
  }

  def strokeText(text: String, x: Double = 0.0, y: Double = 0.0, maxWidth: Double = 10000.0): Unit = {
    canvasContext.strokeText(text, x, y, maxWidth)
  }

  def clear(): Unit = {
    canvasContext.clearRect(0.0, 0.0, canvas.width, canvas.height)
  }

  private def paint2Any(paint: Paint): js.Any = paint match {
    case NoPaint => ""
    case ColorPaint(color) => color.toRGBA
    case LinearGradientPaint(x0, y0, x1, y1, stops) => {
      val g = canvasContext.createLinearGradient(x0, y0, x1, y1)
      stops.foreach { stop =>
        g.addColorStop(stop.offset, stop.color.toRGBA)
      }
      g
    }
    case RadialGradientPaint(x0, y0, r0, x1, y1, r1, stops) => {
      val g = canvasContext.createRadialGradient(x0, y0, r0, x1, y1, r1)
      stops.foreach { stop =>
        g.addColorStop(stop.offset, stop.color.toRGBA)
      }
      g
    }
    case PatternPaint(createPattern) => createPattern(canvasContext)
    case _ => throw new RuntimeException(s"Unsupported paint: $paint")
  }
}