package org.hyperscala.persistence

import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object ColorPersistence extends ValuePersistence[Color] {
  def fromString(s: String, name: String, clazz: Class[_]) = Color(s)

  def toString(t: Color, name: String, clazz: Class[_]) = t match {
    case null => null
    case _ if t.alpha != 1.0 => s"rgba(${t.int.red}, ${t.int.green}, ${t.int.blue}, ${t.alpha})"
    case _ => t.hex.rgb
  }
}
