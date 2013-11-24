package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object SetStringPersistence extends ValuePersistence[Set[String]] {
  def fromString(s: String, name: String, clazz: Class[_]) = s.split(" ").toSet

  def toString(t: Set[String], name: String, clazz: Class[_]) = t.mkString(" ")
}
