package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object ListStringPersistence extends ValuePersistence[List[String]] {
  def fromString(s: String, clazz: Class[_]) = s.split(" ").toList

  def toString(t: List[String], clazz: Class[_]) = t.mkString(" ")
}
