package org.hyperscala.persistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ListStringPersistence extends ValuePersistence[List[String]] {
  def fromString(s: String, name: String, clazz: Class[_]) = s.split(" ").toList

  def toString(t: List[String], name: String, clazz: Class[_]) = t.mkString(" ")
}
