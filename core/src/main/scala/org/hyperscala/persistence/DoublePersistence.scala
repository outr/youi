package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object DoublePersistence extends ValuePersistence[Double] {
  def fromString(s: String, clazz: Class[_]) = s.collect {
    case c if (c.isDigit || c == '.') => c
  } match {
    case "" => 0.0
    case v => try {
      v.toDouble
    } catch {
      case t: Throwable => 0.0
    }
  }

  def toString(t: Double, clazz: Class[_]) = t.toString
}
