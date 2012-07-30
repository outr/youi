package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ValuePersistence[T] {
  def fromString(s: String, clazz: Class[_]): T

  def toString(t: T, clazz: Class[_]): String
}
