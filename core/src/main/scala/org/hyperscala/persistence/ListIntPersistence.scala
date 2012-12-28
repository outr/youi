package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object ListIntPersistence extends ValuePersistence[List[Int]] {
  def fromString(s: String, clazz: Class[_]) = s.split(",").map(v => v.trim.toInt).toList

  def toString(t: List[Int], clazz: Class[_]) = t.mkString(", ")
}
