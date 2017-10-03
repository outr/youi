package io.youi

import io.youi.paint.{Paint, Stroke}
import io.youi.path.Path
import io.youi.spatial.{Matrix3, Size}
import org.scalajs.dom.{html, _}

import scala.scalajs.js

class Context(val canvas: html.Canvas) {
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

  def translate(x: Double, y: Double): Unit = ctx.translate(x, y)

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
    ctx.drawImage(image, x, y, width, height)
  }

  def drawCanvas(canvas: html.Canvas)
                (x: Double = 0.0,
                 y: Double = 0.0,
                 width: Double = canvas.width,
                 height: Double = canvas.height): Unit = {
    ctx.drawImage(canvas.asInstanceOf[html.Image], x, y, width, height)
  }

  def drawVideo(video: html.Video)
               (x: Double = 0.0,
                y: Double = 0.0,
                width: Double = video.width,
                height: Double = video.height): Unit = {
    ctx.drawImage(video.asInstanceOf[html.Image], x, y, width, height)
  }

  def moveTo(x: Double, y: Double): Unit = ctx.moveTo(Path.fix(x), Path.fix(y))

  def lineTo(x: Double, y: Double): Unit = ctx.lineTo(Path.fix(x), Path.fix(y))

  def quadraticCurveTo(cpx: Double, cpy: Double, x: Double, y: Double): Unit = {
    ctx.quadraticCurveTo(cpx, cpy, x, y)
  }

  def clipRect(x1: Double, y1: Double, x2: Double, y2: Double): Unit = {
    begin()
    moveTo(x1, y1)
    lineTo(x2, y1)
    lineTo(x2, y2)
    lineTo(x1, y2)
    lineTo(x1, y1)
    ctx.clip()
  }

  def begin(): Unit = ctx.beginPath()
  def close(): Unit = ctx.closePath()

  def rect(x: Double, y: Double, width: Double, height: Double): Unit = {
    begin()
    ctx.rect(Path.fix(x), Path.fix(y), width, height)
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
    ctx.lineWidth = stroke.lineWidth
    ctx.setLineDash(js.Array(stroke.lineDash: _*))
    ctx.lineDashOffset = stroke.lineDashOffset
    ctx.lineCap = stroke.lineCap.value
    ctx.lineJoin = stroke.lineJoin.value
    if (apply) ctx.stroke()
  }

  def setShadow(blur: Double, color: Color, x: Double, y: Double): Unit = {
    ctx.shadowBlur = blur
    ctx.shadowColor = color.toRGBA
    ctx.shadowOffsetX = x
    ctx.shadowOffsetY = y
  }

  def setFont(family: String, size: Double, style: String, variant: String, weight: String): Unit = {
    ctx.font = s"$style $variant $weight ${size}px $family"
  }

  def lineJoin(value: String): Unit = ctx.lineJoin = value
  def miterLimit(value: Double): Unit = ctx.miterLimit = value
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
    ctx.fillText(text, x, y, maxWidth)
  }

  def strokeText(text: String, x: Double = 0.0, y: Double = 0.0, maxWidth: Double = 10000.0): Unit = {
    ctx.strokeText(text, x, y, maxWidth)
  }

  def clear(): Unit = {
    ctx.clearRect(0.0, 0.0, canvas.width, canvas.height)
  }
}