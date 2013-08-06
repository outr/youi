package org.hyperscala.ui.convert

import org.powerscala.reflect.EnhancedClass

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StringConverter extends Converter[String] {
  def value2String(value: String) = value

  def string2Value(s: String, clazz: EnhancedClass) = Some(s)
}
