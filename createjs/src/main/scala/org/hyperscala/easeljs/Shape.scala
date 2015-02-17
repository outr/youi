package org.hyperscala.easeljs

import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.powerscala.event.Listenable
import org.powerscala.property.Property
import org.powerscala.{Color, Unique}

/**
 * Matt Hicks <matt@outr.com>
 */
class Shape private[easeljs](val stage: Stage) extends Listenable {
  val id = Unique()

  lazy val alpha = new ShapeProperty[Double]("alpha", 1.0, this)
  lazy val cursor = new ShapeProperty[String]("cursor", null, this)
  lazy val mouseEnabled = new ShapeProperty[Boolean]("mouseEnabled", true, this)
  lazy val name = new ShapeProperty[String]("name", null, this)
  lazy val regX = new ShapeProperty[Double]("regX", 0.0, this)
  lazy val regY = new ShapeProperty[Double]("regY", 0.0, this)
  lazy val rotation = new ShapeProperty[Double]("rotation", 0.0, this)
  lazy val scaleX = new ShapeProperty[Double]("scaleX", 1.0, this)
  lazy val scaleY = new ShapeProperty[Double]("scaleY", 1.0, this)
  lazy val skewX = new ShapeProperty[Double]("skewX", 1.0, this)
  lazy val skewY = new ShapeProperty[Double]("skewY", 1.0, this)
  lazy val snapToPixel = new ShapeProperty[Boolean]("snapToPixel", true, this)
  lazy val tickEnabled = new ShapeProperty[Boolean]("tickEnabled", true, this)
  lazy val visible = new ShapeProperty[Boolean]("visible", true, this)
  lazy val x = new ShapeProperty[Double]("x", 0.0, this)
  lazy val y = new ShapeProperty[Double]("y", 0.0, this)

  lazy val graphics = new ShapeGraphics(this)

  private[easeljs] def call(content: String) = stage.send(JavaScriptString(s"EaselJS.shape('$id', '${stage.canvas.identity}').$content"))
}

class ShapeGraphics private[easeljs](shape: Shape) {
  def arc(x: Double,
          y: Double,
          radius: Double,
          startAngle: Double,
          endAngle: Double,
          anticlockwise: Boolean = false) = call("a", x, y, radius, startAngle, endAngle, anticlockwise)

  def arcTo(x1: Double,
            y1: Double,
            x2: Double,
            y2: Double,
            radius: Double) = call("at", x1, y1, x2, y2, radius)

  // beginBitmapFill(image, repetition, matrix)

  // beginBitmapStroke(image, repetition)

  def beginFill(color: Color) = call("f", color.toCSS)

  def beginLinearGradientFill(colors: List[Color],
                              ratios: List[Double],
                              x0: Double,
                              y0: Double,
                              x1: Double,
                              y1: Double) = call("lf", colors, ratios, x0, y0, x1, y1)

  def beginLinearGradientStroke(colors: List[Color],
                                ratios: List[Double],
                                x0: Double,
                                y0: Double,
                                x1: Double,
                                y1: Double) = call("ls", colors, ratios, x0, y0, x1, y1)

  def beginRadialGradientFill(colors: List[Color],
                              ratios: List[Double],
                              x0: Double,
                              y0: Double,
                              r0: Double,
                              x1: Double,
                              y1: Double,
                              r1: Double) = call("rf", colors, ratios, x0, y0, r0, x1, y1, r1)

  def beginRadialGradientStroke(colors: List[Color],
                                ratios: List[Double],
                                x0: Double,
                                y0: Double,
                                r0: Double,
                                x1: Double,
                                y1: Double,
                                r1: Double) = call("rs", colors, ratios, x0, y0, r0, x1, y1, r1)

  def beginStroke(color: Color) = call("s", color)

  def bezierCurveTo(cp1x: Double,
                    cp1y: Double,
                    cp2x: Double,
                    cp2y: Double,
                    x: Double,
                    y: Double) = call("bt", cp1x, cp1y, cp2x, cp2y, x, y)

  def clear() = call("c")

  def closePath() = call("cp")

  def decodePath(str: String) = call("p", str)

  def drawCircle(x: Double, y: Double, radius: Double) = call("dc", x, y, radius)

  def drawEllipse(x: Double, y: Double, w: Double, h: Double) = call("de", x, y, w, h)

  def drawPolyStar(x: Double,
                   y: Double,
                   radius: Double,
                   sides: Double,
                   pointSize: Double,
                   angle: Double) = call("dp", x, y, radius, sides, pointSize, angle)

  def drawRect(x: Double, y: Double, w: Double, h: Double) = call("drawRect", x, y, w, h)

  def drawRoundRect(x: Double, y: Double, w: Double, h: Double, radius: Double) = call("drawRoundRect", x, y, w, h, radius)

  def drawRoundRectComplex(x: Double,
                           y: Double,
                           w: Double,
                           h: Double,
                           radiusTL: Double,
                           radiusTR: Double,
                           radiusBR: Double,
                           radiusBL: Double) = call("rc", x, y, w, h, radiusTL, radiusTR, radiusBR, radiusBL)

  def endFill() = call("ef")

  def endStroke() = call("es")

  def lineTo(x: Double, y: Double) = call("lt", x, y)

  def moveTo(x: Double, y: Double) = call("mt", x, y)

  def quadraticCurveTo(cpx: Double, cpy: Double, x: Double, y: Double) = call("quadraticCurveTo", cpx, cpy, x, y)

  def rect(x: Double, y: Double, w: Double, h: Double) = call("r", x, y, w, h)

  def setStrokeStyle(thickness: Double,
                     caps: Int = 0,
                     joints: Int = 0,
                     miterLimit: Int = 10,
                     ignoreScale: Boolean = false) = call("ss", thickness, caps, joints, miterLimit, ignoreScale)

  def store() = call("store")

  def unstore() = call("unstore")

  def updateCache(compositeOperation: String) = call("updateCache", compositeOperation)

  def cache(x: Double,
            y: Double,
            width: Double,
            height: Double,
            scale: Double = 1.0) = call("cache", x, y, width, height, scale)

  private def call(function: String, args: Any*) = {
    shape.call(s"graphics.$function(${args.map(a => JavaScriptContent.toJS(a)).mkString(", ")})")
    this
  }
}

class ShapeProperty[T](name: String, default: T, shape: Shape, val stringify: T => String = (t: T) => JavaScriptContent.toJS(t))(implicit manifest: Manifest[T]) extends Property[T](default = Option(default))(shape, manifest) {
  change.on {
    case evt => shape.call(s"$name = ${stringify(evt.newValue)}")
  }
}