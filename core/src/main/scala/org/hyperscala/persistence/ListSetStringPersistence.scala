package org.hyperscala.persistence

import scala.collection.immutable.ListSet

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object ListSetStringPersistence extends ValuePersistence[ListSet[String]] {
  def fromString(s: String, name: String, clazz: Class[_]) = ListSet(s.split(" "): _*)

  def toString(t: ListSet[String], name: String, clazz: Class[_]) = t.mkString(" ")
}
