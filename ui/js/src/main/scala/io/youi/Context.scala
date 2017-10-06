package io.youi

import io.youi.paint.{Paint, Stroke}
import io.youi.path.Path
import io.youi.spatial.{Matrix3, Size}
import org.scalajs.dom.{html, _}

import scala.scalajs.js

class Context(val canvas: html.Canvas, _ratio: => Double) {
  private var adjustScaleX: Double = 1.0
  private var adjustScaleY: Double = 1.0

  def ratioX: Double = _ratio * adjustScaleX
  def ratioY: Double = _ratio * adjustScaleY
  def ctx: CanvasRenderingContext2D = canvas.context
  
  def width: Double = canvas.width.toDouble
  def height: Double = canvas.height.toDouble
  
  def transform(matrix: Matrix3): Unit = {
    ctx.setTransform(matrix.m00, matrix.m01, matrix.m10, matrix.m11, matrix.m02, matrix.m12)
  }
  def scale(x: Double, y: Double): Unit = ctx.scale(x, y)

  def save(): Unit = ctx.save()
  def restore(): Unit = ctx.restore()

  def state[R](f: => R): R = try {
    save()
    f
  } finally {
    restore()
  }

  def identity(): Unit = transform(Matrix3.Identity)

  def withScale[R](x: Double = 1.0, y: Double = 1.0)(f: => R): R = {
    val ox = adjustScaleX
    val oy = adjustScaleY
    adjustScaleX *= x
    adjustScaleY *= y
    try {
      f
    } finally {
      adjustScaleX = ox
      adjustScaleY = oy
    }
  }

  /*def transform(component: Component, multiply: Boolean = false): Unit = {
    if (multiply) {
      component.parent().foreach(transform(_, multiply))
    }
    def m(value: Double): Double = value * ui.dpiMultiplier
    ctx.translate(m(component.position.x), m(component.position.y))
    ctx.translate(m(component.pivot.x()), m(component.pivot.y()))
    ctx.rotate(m(component.rotation() * (math.Pi * 2.0)))
    ctx.translate(m(-component.pivot.x()), m(-component.pivot.y()))
  }*/

  def translate(x: Double, y: Double): Unit = ctx.translate(x * ratioX, y * ratioY)

  def opacity_=(value: Double): Unit = ctx.globalAlpha = value
  def opacity: Double = ctx.globalAlpha

  /*def draw(component: Component): Unit = {
    val canvas = component.drawer.context.canvas
    ctx.drawImage(canvas.asInstanceOf[html.Image], 0.0, 0.0, canvas.width, canvas.height)
  }*/

  def drawImage(image: html.Image)
               (x: Double = 0.0,
                y: Double = 0.0,
                width: Double = image.width,
                height: Double = image.height): Unit = {
    ctx.drawImage(image, x * ratioX, y * ratioY, width * ratioX, height * ratioY)
  }

  def drawCanvas(canvas: html.Canvas)
                (x: Double = 0.0,
                 y: Double = 0.0,
                 width: Double = canvas.width,
                 height: Double = canvas.height): Unit = {
    ctx.drawImage(canvas.asInstanceOf[html.Image], x * ratioX, y * ratioY, width * ratioX, height * ratioY)
  }

  def drawVideo(video: html.Video)
               (x: Double = 0.0,
                y: Double = 0.0,
                width: Double = video.width,
                height: Double = video.height): Unit = {
    ctx.drawImage(video.asInstanceOf[html.Image], x * ratioX, y * ratioY, width * ratioX, height * ratioY)
  }

  def moveTo(x: Double, y: Double): Unit = {
    val fx = Path.fix(x * ratioX)
    val fy = Path.fix(y * ratioY)
    ctx.moveTo(fx, fy)
  }

  def lineTo(x: Double, y: Double): Unit = {
    val fx = Path.fix(x * ratioX)
    val fy = Path.fix(y * ratioY)
    ctx.lineTo(fx, fy)
  }

  def quadraticCurveTo(cpx: Double, cpy: Double, x: Double, y: Double): Unit = {
    ctx.quadraticCurveTo(cpx * ratioX, cpy * ratioY, x * ratioX, y * ratioY)
  }

  def bezierCurveTo(cp1x: Double, cp1y: Double, cp2x: Double, cp2y: Double, x: Double, y: Double): Unit = {
    ctx.bezierCurveTo(cp1x * ratioX, cp1y * ratioY, cp2x * ratioX, cp2y * ratioY, x * ratioX, y * ratioY)
  }

  def clipRect(x1: Double, y1: Double, x2: Double, y2: Double): Unit = {
    begin()
    moveTo(x1 * ratioX, y1 * ratioY)
    lineTo(x2 * ratioX, y1 * ratioY)
    lineTo(x2 * ratioX, y2 * ratioY)
    lineTo(x1 * ratioX, y2 * ratioY)
    lineTo(x1 * ratioX, y1 * ratioY)
    ctx.clip()
  }

  def begin(): Unit = ctx.beginPath()
  def close(): Unit = ctx.closePath()

  def rect(x: Double, y: Double, width: Double, height: Double): Unit = {
    begin()
    ctx.rect(Path.fix(x * ratioX), Path.fix(y * ratioY), width * ratioX, height * ratioY)
    close()
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
    ctx.fillStyle = paint.asJS(this)
    if (apply) ctx.fill()
  }

  def stroke(stroke: Stroke, apply: Boolean): Unit = if (stroke.nonEmpty) {
    ctx.strokeStyle = stroke.paint.asJS(this)
    ctx.lineWidth = stroke.lineWidth * ratioX
    ctx.setLineDash(js.Array(stroke.lineDash: _*))
    ctx.lineDashOffset = stroke.lineDashOffset * ratioX
    ctx.lineCap = stroke.lineCap.value
    ctx.lineJoin = stroke.lineJoin.value
    if (apply) ctx.stroke()
  }

  def setShadow(blur: Double, color: Color, x: Double, y: Double): Unit = {
    ctx.shadowBlur = blur
    ctx.shadowColor = color.toRGBA
    ctx.shadowOffsetX = x * ratioX
    ctx.shadowOffsetY = y * ratioY
  }

  def setFont(family: String, size: Double, style: String, variant: String, weight: String): Unit = {
    ctx.font = s"$style $variant $weight ${size * ratioX}px $family"
  }

  def lineJoin(value: String): Unit = ctx.lineJoin = value
  def miterLimit(value: Double): Unit = ctx.miterLimit = value * ratioX
  def textBaseline(value: String): Unit = ctx.textBaseline = value

  def measureText(text: String, size: Size = Size.zero): Size = {
    val div = dom.create[html.Div]("div")
    div.style.font = ctx.font
    div.style.display = "inline-block"
    div.innerHTML = text
    document.body.appendChild(div)
    val width = div.clientWidth
    val height = div.clientHeight
    document.body.removeChild(div)
    size.set(width, height)
  }

  def fillText(text: String, x: Double = 0.0, y: Double = 0.0, maxWidth: Double = 10000.0): Unit = {
    ctx.textBaseline = "top"
    ctx.fillText(text, x * ratioX, y * ratioY, maxWidth * ratioX)
  }

  def strokeText(text: String, x: Double = 0.0, y: Double = 0.0, maxWidth: Double = 10000.0): Unit = {
    ctx.strokeText(text, x * ratioX, y * ratioY, maxWidth * ratioX)
  }

  def clear(): Unit = {
    ctx.clearRect(0.0, 0.0, canvas.width, canvas.height)
  }
}