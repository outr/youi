package org.hyperscala.ui.convert

/**
 * @author Matt Hicks <matt@outr.com>
 */
class IntConverter(pre: String = "", post: String = "") extends Converter[Int] {
  def value2String(value: Int) = s"$pre$value$post"

  def string2Value(s: String) = DoubleConverter.string2Value(s).map(d => math.round(d).toInt)
}

object IntConverter extends IntConverter(pre = "", post = "")