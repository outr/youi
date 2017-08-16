package io.youi

import io.youi.component.Component
import io.youi.paint.{ColorPaint, NoPaint, Paint}
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
    component.parent.foreach(transform)
    context.translate(component.position.x(), component.position.y())
    context.translate(component.pivot.x(), component.pivot.y())
    context.rotate(component.rotation() * (math.Pi * 2.0))
    context.translate(-component.pivot.x(), -component.pivot.y())
  }

  def draw(component: Component): Unit = {
    val canvas = component.drawable.canvas
    context.drawImage(canvas.asInstanceOf[html.Image], 0.0, 0.0, canvas.width, canvas.height)
  }

  def draw(image: html.Image)
          (x: Double = 0.0,
           y: Double = 0.0,
           width: Double = image.width,
           height: Double = image.height): Unit = {
    context.drawImage(image, x, y, width, height)
  }

  def rect(x: Double, y: Double, width: Double, height: Double): Unit = {
    context.rect(x, y, width, height)
  }

  def fill(paint: Paint, apply: Boolean): Unit = {
    if (paint != Paint.none) {
      context.fillStyle = paint2Any(paint)
      if (apply) context.fill()
    }
  }

  def setFont(family: String, size: Double, style: String, variant: String, weight: String): Unit = {
    context.font = s"$style $variant $weight ${size}px $family"
  }

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
    case ColorPaint(color) => Color.toCSS(color.red, color.green, color.blue, color.alpha)
    case _ => throw new RuntimeException(s"Unsupported paint: $paint")
  }
}
