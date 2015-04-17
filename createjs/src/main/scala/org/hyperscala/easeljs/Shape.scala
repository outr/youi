package org.hyperscala.easeljs

import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.powerscala.event.Listenable
import org.powerscala.property.Property
import org.powerscala.{Color, Unique}

/**
 * Matt Hicks <matt@outr.com>
 */
class Shape private[easeljs](val stage: Stage) extends Listenable {
  val id = Unique()

  import ShapeProperty._
  lazy val alpha = new ShapeAttribute(Alpha, this)
  lazy val cursor = new ShapeAttribute(Cursor, this)
  lazy val mouseEnabled = new ShapeAttribute(MouseEnabled, this)
  lazy val name = new ShapeAttribute(Name, this)
  lazy val regX = new ShapeAttribute(RegX, this)
  lazy val regY = new ShapeAttribute(RegY, this)
  lazy val rotation = new ShapeAttribute(Rotation, this)
  lazy val scaleX = new ShapeAttribute(ScaleX, this)
  lazy val scaleY = new ShapeAttribute(ScaleY, this)
  lazy val skewX = new ShapeAttribute(SkewX, this)
  lazy val skewY = new ShapeAttribute(SkewY, this)
  lazy val snapToPixel = new ShapeAttribute(SnapToPixel, this)
  lazy val tickEnabled = new ShapeAttribute(TickEnabled, this)
  lazy val visible = new ShapeAttribute(Visible, this)
  lazy val x = new ShapeAttribute(X, this)
  lazy val y = new ShapeAttribute(Y, this)

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

class ShapeAttribute[T](val property: ShapeProperty[T], shape: Shape, val stringify: T => String = (t: T) => JavaScriptContent.toJS(t)) extends Property[T](default = Option(property.default))(shape, property.manifest) {
  change.on {
    case evt => shape.call(s"${property.attributeName} = ${stringify(evt.newValue)}")
  }
}

sealed abstract class ShapeProperty[T](val default: T)(implicit val manifest: Manifest[T]) extends EnumEntry {
  def attributeName = name.charAt(0).toLower + name.substring(1)

  def value(t: T) = ShapePropertyValue(this, t)
}

object ShapeProperty extends Enumerated[ShapeProperty[_]] {
  case object Alpha extends ShapeProperty[Double](1.0)
  case object Cursor extends ShapeProperty[String](null)
  case object MouseEnabled extends ShapeProperty[Boolean](true)
  case object Name extends ShapeProperty[String](null)
  case object RegX extends ShapeProperty[Double](0.0)
  case object RegY extends ShapeProperty[Double](0.0)
  case object Rotation extends ShapeProperty[Double](0.0)
  case object ScaleX extends ShapeProperty[Double](1.0)
  case object ScaleY extends ShapeProperty[Double](1.0)
  case object SkewX extends ShapeProperty[Double](1.0)
  case object SkewY extends ShapeProperty[Double](1.0)
  case object SnapToPixel extends ShapeProperty[Boolean](true)
  case object TickEnabled extends ShapeProperty[Boolean](true)
  case object Visible extends ShapeProperty[Boolean](true)
  case object X extends ShapeProperty[Double](0.0)
  case object Y extends ShapeProperty[Double](0.0)

  val values = findValues.toVector
}

case class ShapePropertyValue[T](property: ShapeProperty[T], value: T)