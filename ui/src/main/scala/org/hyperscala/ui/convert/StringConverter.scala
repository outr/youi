package org.hyperscala.ui.convert

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StringConverter extends Converter[String] {
  def value2String(value: String) = value

  def string2Value(s: String) = Some(s)
}
