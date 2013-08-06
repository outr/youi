package org.hyperscala.ui.convert

import org.powerscala.reflect.EnhancedClass

/**
 * @author Matt Hicks <matt@outr.com>
 */
class LongConverter(pre: String = "", post: String = "") extends Converter[Long] {
  def value2String(value: Long) = s"$pre$value$post"

  def string2Value(s: String, clazz: EnhancedClass) = DoubleConverter.string2Value(s, clazz).map(d => math.round(d))
}

object LongConverter extends LongConverter(pre = "", post = "")