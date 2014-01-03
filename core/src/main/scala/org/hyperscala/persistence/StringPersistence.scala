package org.hyperscala.persistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StringPersistence extends ValuePersistence[String] {
  def fromString(s: String, name: String, clazz: Class[_]) = s

  def toString(t: String, name: String, clazz: Class[_]) = t match {
    case null => ""
    case _ => t
  }
}
