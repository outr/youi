package org.hyperscala.ui.convert

import org.powerscala.reflect.EnhancedClass

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DoubleConverter(pre: String = "", post: String = "") extends Converter[Double] {
  def value2String(value: Double) = s"$pre$value$post"

  def string2Value(s: String, clazz: EnhancedClass): Option[Double] = DoubleConverter.string2Value(s, clazz)
}

object DoubleConverter extends DoubleConverter(pre = "", post = "") {
  val Regex = """(\d+([.]\d+)?)""".r

  override def string2Value(s: String, clazz: EnhancedClass) = s match {
    case DoubleConverter.Regex(d, extra) => try {
      Some(d.toDouble)
    } catch {
      case t: Throwable => None
    }
    case _ => None
  }
}