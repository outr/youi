package io.youi

import io.youi.component.Component
import io.youi.paint._
import io.youi.path.Path
import io.youi.spatial.Matrix3
import org.scalajs.dom.{CanvasRenderingContext2D, html}

import scala.scalajs.js
import org.scalajs.dom._
import reactify._

class Context(val canvas: html.Canvas) {
  lazy val canvasContext = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def width: Double = canvas.width.toDouble
  def height: Double = canvas.height.toDouble

  def transform(matrix: Matrix3): Unit = {
    canvasContext.setTransform(matrix.m00, matrix.m01, matrix.m10, matrix.m11, matrix.m02, matrix.m12)
  }
  def scale(x: Double, y: Double): Unit = canvasContext.scale(x, y)

  def save(): Unit = canvasContext.save()
  def restore(): Unit = canvasContext.restore()

  def state[R](f: => R): R = try {
    save()
    f
  } finally {
    restore()
  }

  def transform(component: Component, multiply: Boolean = false): Unit = {
    if (!multiply) transform(Matrix3.Identity)
    def m(value: Double): Double = value * ui.dpiMultiplier
    canvasContext.translate(m(component.position.x), m(component.position.y))
    canvasContext.translate(m(component.pivot.x()), m(component.pivot.y()))
    canvasContext.rotate(m(component.rotation() * (math.Pi * 2.0)))
    canvasContext.translate(m(-component.pivot.x()), m(-component.pivot.y()))
  }

  def translate(x: Double, y: Double): Unit = canvasContext.translate(x, y)

  def opacity_=(value: Double): Unit = canvasContext.globalAlpha = value
  def opacity: Double = canvasContext.globalAlpha

  def draw(component: Component): Unit = {
    val canvas = component.drawer.context.canvas
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

  def moveTo(x: Double, y: Double): Unit = canvasContext.moveTo(Path.fix(x), Path.fix(y))

  def lineTo(x: Double, y: Double): Unit = canvasContext.lineTo(Path.fix(x), Path.fix(y))

  def quadraticCurveTo(cpx: Double, cpy: Double, x: Double, y: Double): Unit = {
    canvasContext.quadraticCurveTo(cpx, cpy, x, y)
  }

  def begin(): Unit = canvasContext.beginPath()
  def close(): Unit = canvasContext.closePath()

  def rect(x: Double, y: Double, width: Double, height: Double): Unit = {
    canvasContext.rect(Path.fix(x), Path.fix(y), width, height)
  }

  def roundedRect(x: Double, y: Double, width: Double, height: Double, radius: Double): Unit = {
    begin()
    moveTo(x + radius, y)
    lineTo(x + width - radius, y)
    quadraticCurveTo(x + width, y, x + width, y + radius)
    lineTo(x + width, y + height - radius)
    quadraticCurveTo(x + width, y + height, x + width - radius, y + height)
    lineTo(x + radius, y + height)
    quadraticCurveTo(x, y + height, x, y + height - radius)
    lineTo(x, y + radius)
    quadraticCurveTo(x, y, x + radius, y)
    close()
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

  private def paint2Any(paint: Paint): js.Any = paint.asJS(this)
}