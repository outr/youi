package org.hyperscala.persistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ValuePersistence[T] {
  def fromString(s: String, name: String, clazz: Class[_]): T

  def toString(t: T, name: String, clazz: Class[_]): String
}
