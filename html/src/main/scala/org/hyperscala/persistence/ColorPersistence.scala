package org.hyperscala.persistence

import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object ColorPersistence extends ValuePersistence[Color] {
  def fromString(s: String, clazz: Class[_]) = Color.immutable(s)

  def toString(t: Color, clazz: Class[_]) = t.hex.rgb
}
