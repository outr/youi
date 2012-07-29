package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object IntPersistence extends ValuePersistence[Int] {
  def fromString(s: String, clazz: Class[_]) = s.toInt

  def toString(t: Int, clazz: Class[_]) = t.toString
}
