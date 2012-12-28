package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object ListDoublePersistence extends ValuePersistence[List[Double]] {
  def fromString(s: String, clazz: Class[_]) = s.split(",").map(v => v.trim.toDouble).toList

  def toString(t: List[Double], clazz: Class[_]) = t.mkString(", ")
}
