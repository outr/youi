package org.hyperscala.ui.convert

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.powerscala.reflect.EnhancedClass

/**
 * @author Matt Hicks <matt@outr.com>
 */
object EnumConverter extends Converter[EnumEntry] {
  def value2String(value: EnumEntry) = value.name

  def string2Value(s: String, clazz: EnhancedClass) = {
    Option(clazz.instance.get.asInstanceOf[Enumerated[EnumEntry]].apply(s))
  }
}