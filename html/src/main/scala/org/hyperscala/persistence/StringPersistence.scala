package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object StringPersistence extends ValuePersistence[String] {
  def fromString(s: String, clazz: Class[_]) = s

  def toString(t: String, clazz: Class[_]) = t
}
