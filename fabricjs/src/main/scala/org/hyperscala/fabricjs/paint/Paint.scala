package org.hyperscala.fabricjs.paint

import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.Color
import org.hyperscala.fabricjs._

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed trait Paint {
  def toJS(obj: Object, paintType: String): String
}

case class ColorPaint(color: Color) extends Paint {
  def toJS(obj: Object, paintType: String) = JavaScriptContent.toJS(color)
}

trait GradientPaint extends Paint

case class RadialGradient(x1: Double = 0.0,
                          y1: Double = 0.0,
                          x2: Double = 0.0,
                          y2: Double = 0.0,
                          r1: Double = 0.0,
                          r2: Double = 0.0,
                          colorStops: List[ColorStop] = Nil) extends GradientPaint {
  def toJS(obj: Object, paintType: String) =
    s"""FabricJS.object['${obj.id}'].setGradient('$paintType', {
       |  type: 'radial',
       |  x1: $x1,
       |  y1: $y1,
       |  x2: $x2,
       |  y2: $y2,
       |  r1: $r1,
       |  r2: $r2,
       |  colorStops: ${colorStops.map(cs => s"${cs.percentage}: '${cs.color.toCSS}'").mkString("{", ", ", "}")}
       |});
     """.stripMargin
}

case class LinearGradient(x1: Double = 0.0,
                          y1: Double = 0.0,
                          x2: Double = 0.0,
                          y2: Double = 0.0,
                          colorStops: List[ColorStop] = Nil) extends GradientPaint {
  def toJS(obj: Object, paintType: String) =
    s"""FabricJS.object['${obj.id}'].setGradient('$paintType', {
        |  type: 'linear',
        |  x1: $x1,
        |  y1: $y1,
        |  x2: $x2,
        |  y2: $y2,
        |  colorStops: ${colorStops.map(cs => s"${cs.percentage}: '${cs.color.toCSS}'").mkString("{", ", ", "}")}
        |});
     """.stripMargin
}

case class ColorStop(percentage: Double = 0.0, color: Color)