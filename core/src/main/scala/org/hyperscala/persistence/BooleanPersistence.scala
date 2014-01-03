package org.hyperscala.persistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
object BooleanPersistence extends ValuePersistence[Boolean] {
  def fromString(s: String, name: String, clazz: Class[_]) = s != null && s.nonEmpty

  def toString(t: Boolean, name: String, clazz: Class[_]) = t match {
    case true => if (name == null) "" else name
    case false => null
  }
}