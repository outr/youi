package org.hyperscala.persistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
object CharPersistence extends ValuePersistence[Char] {
  def fromString(s: String, name: String, clazz: Class[_]) = s.charAt(0)

  def toString(t: Char, name: String, clazz: Class[_]) = t.toString
}
