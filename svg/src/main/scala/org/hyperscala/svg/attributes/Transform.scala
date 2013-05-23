package org.hyperscala.svg.attributes

import org.hyperscala.AttributeValue
import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class Transform extends AttributeValue

object Transform {
  def matrix(a: Double, b: Double, c: Double, d: Double, e: Double, f: Double) = Matrix(a, b, c, d, e, f)
  def translate(x: Double = 0.0, y: Double = 0.0) = Translate(x, y)
  def scale(x: Double = 1.0, y: Double = 1.0) = Scale(x, y)
  def rotate(angle: Double, x: Double = Double.NaN, y: Double = Double.NaN) = Rotate(angle, x, y)
  def skewX(angle: Double) = SkewX(angle)
  def skewY(angle: Double) = SkewY(angle)

  def parse(s: String, transforms: List[Transform] = Nil): List[Transform] = {
    val data = s.trim match {
      case d if (d.startsWith(",")) => d.substring(1).trim
      case d => d
    }
    if (data.nonEmpty) {
      val start = data.indexOf('(')
      val end = data.indexOf(')')
      if (start != -1 && end > start) {
        val name = data.substring(0, start)
        val args = data.substring(start + 1, end).split(",").map(s => s.trim.toDouble)
        val transform = name match {
          case "matrix" => matrix(args(0), args(1), args(2), args(3), args(4), args(5))
          case "translate" => translate(args(0), args(1))
          case "scale" => scale(args(0), args(1))
          case "rotate" => rotate(args(0), args(1), args(2))
          case "skewX" => skewX(args(0))
          case "skewY" => skewY(args(0))
        }
        parse(data.substring(end + 1), transform :: transforms)
      } else {
        throw new RuntimeException("Unable to parse string: %s".format(s))
      }
    } else {
      transforms.reverse
    }
  }
}

case class Matrix(a: Double, b: Double, c: Double, d: Double, e: Double, f: Double) extends Transform {
  def value = "matrix(%s, %s, %s, %s, %s, %s)".format(a, b, c, d, e, f)
}

case class Translate(x: Double = 0.0, y: Double = 0.0) extends Transform {
  def value = "translate(%s, %s)".format(x, y)
}

case class Scale(x: Double = 1.0, y: Double = 1.0) extends Transform {
  def value = "scale(%s, %s)".format(x, y)
}

case class Rotate(angle: Double, x: Double = Double.NaN, y: Double = Double.NaN) extends Transform {
  def value = if (x.isNaN || y.isNaN) {
    "rotate(%s)".format(angle)
  } else {
    "rotate(%s, %s, %s)".format(angle, x, y)
  }
}

case class SkewX(angle: Double) extends Transform {
  def value = "skeyX(%s)".format(angle)
}

case class SkewY(angle: Double) extends Transform {
  def value = "skeyY(%s)".format(angle)
}

object ListTransformPersistence extends ValuePersistence[List[Transform]] {
  def fromString(s: String, name: String, clazz: Class[_]): List[Transform] = Transform.parse(s)

  def toString(t: List[Transform], name: String, clazz: Class[_]): String = t.map(t => t.value).mkString(", ")
}