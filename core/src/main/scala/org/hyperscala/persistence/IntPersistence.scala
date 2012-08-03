package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object IntPersistence extends ValuePersistence[Int] {
  def fromString(s: String, clazz: Class[_]) = s.collect {
    case c if (c.isDigit) => c
  } match {
    case "" => 0
    case v => v.toInt
  }

  def toString(t: Int, clazz: Class[_]) = t.toString
}
