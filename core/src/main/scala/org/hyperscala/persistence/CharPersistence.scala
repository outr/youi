package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object CharPersistence extends ValuePersistence[Char] {
  def fromString(s: String, name: String, clazz: Class[_]) = s.charAt(0)

  def toString(t: Char, name: String, clazz: Class[_]) = t.toString
}
