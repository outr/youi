package io.youi

import io.youi.component.Component
import io.youi.paint.{ColorPaint, NoPaint, Paint, Stroke}
import io.youi.spatial.Matrix3
import org.scalajs.dom.{CanvasRenderingContext2D, html}

import scala.scalajs.js
import org.scalajs.dom._

class Context(val canvas: html.Canvas) {
  private lazy val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def width: Double = canvas.width.toDouble
  def height: Double = canvas.height.toDouble

  def transform(matrix: Matrix3): Unit = {
    context.setTransform(matrix.m00, matrix.m01, matrix.m10, matrix.m11, matrix.m02, matrix.m12)
  }

  def save(): Unit = context.save()
  def restore(): Unit = context.restore()

  def state[R](f: => R): R = try {
    save()
    f
  } finally {
    restore()
  }

  def transform(component: Component): Unit = {
    transform(Matrix3.Identity)
    context.translate(component.position.x(), component.position.y())
    context.translate(component.pivot.x(), component.pivot.y())
    context.rotate(component.rotation() * (math.Pi * 2.0))
    context.translate(-component.pivot.x(), -component.pivot.y())
  }

  def draw(component: Component): Unit = {
    val canvas = component.drawable.canvas
    context.drawImage(canvas.asInstanceOf[html.Image], 0.0, 0.0, canvas.width, canvas.height)
  }

  def drawImage(image: html.Image)
               (x: Double = 0.0,
                y: Double = 0.0,
                width: Double = image.width,
                height: Double = image.height): Unit = {
    context.drawImage(image, x, y, width, height)
  }

  def drawCanvas(canvas: html.Canvas)
                (x: Double = 0.0,
                 y: Double = 0.0,
                 width: Double = canvas.width,
                 height: Double = canvas.height): Unit = {
    context.drawImage(canvas.asInstanceOf[html.Image], x, y, width, height)
  }

  def drawVideo(video: html.Video)
               (x: Double = 0.0,
                y: Double = 0.0,
                width: Double = video.width,
                height: Double = video.height): Unit = {
    context.drawImage(video.asInstanceOf[html.Image], x, y, width, height)
  }

  def rect(x: Double, y: Double, width: Double, height: Double): Unit = {
    context.rect(x, y, width, height)
  }

  def fill(paint: Paint, apply: Boolean): Unit = if (paint.nonEmpty) {
    context.fillStyle = paint2Any(paint)
    if (apply) context.fill()
  }

  def stroke(stroke: Stroke, apply: Boolean): Unit = if (stroke.nonEmpty) {
    context.strokeStyle = paint2Any(stroke.paint)
    context.lineWidth = stroke.lineWidth
    context.setLineDash(js.Array(stroke.lineDash: _*))
    context.lineDashOffset = stroke.lineDashOffset
    context.lineCap = stroke.lineCap.value
    context.lineJoin = stroke.lineJoin.value
    if (apply) context.stroke()
  }

  def setShadow(blur: Double, color: Color, x: Double, y: Double): Unit = {
    context.shadowBlur = blur
    context.shadowColor = color.toRGBA
    context.shadowOffsetX = x
    context.shadowOffsetY = y
  }

  def setFont(family: String, size: Double, style: String, variant: String, weight: String): Unit = {
    context.font = s"$style $variant $weight ${size}px $family"
  }

  def lineJoin(value: String): Unit = context.lineJoin = value
  def miterLimit(value: Double): Unit = context.miterLimit = value
  def textBaseline(value: String): Unit = context.textBaseline = value

  def measureText(text: String, size: Size = Size.zero): Size = {
    val div = dom.create[html.Div]("div")
    div.style.font = context.font
    div.style.display = "inline-block"
    div.innerHTML = text
    document.body.appendChild(div)
    val width = div.clientWidth
    val height = div.clientHeight
    document.body.removeChild(div)
    size.set(width, height)
  }

  def fillText(text: String, x: Double = 0.0, y: Double = 0.0, maxWidth: Double = 10000.0): Unit = {
    context.textBaseline = "top"
    context.fillText(text, x, y, maxWidth)
  }

  def strokeText(text: String, x: Double = 0.0, y: Double = 0.0, maxWidth: Double = 10000.0): Unit = {
    context.strokeText(text, x, y, maxWidth)
  }

  def clear(): Unit = {
    context.clearRect(0.0, 0.0, canvas.width, canvas.height)
  }

  private def paint2Any(paint: Paint): js.Any = paint match {
    case NoPaint => ""
    case ColorPaint(color) => color.toRGBA
    case _ => throw new RuntimeException(s"Unsupported paint: $paint")
  }
}
