package org.hyperscala.persistence

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.powerscala.reflect._
import org.hyperscala.AttributeValue

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait EnumEntryPersistence[E <: EnumEntry with AttributeValue] extends ValuePersistence[E] {
  def fromString(s: String, name: String, clazz: Class[_]) = {
    val ec: EnhancedClass = clazz
    val companion = ec.companion.getOrElse(throw new RuntimeException("No companion for %s".format(clazz.getName))).instance.get
    companion.asInstanceOf[Enumerated[_]](s).asInstanceOf[E]
  }

  def toString(t: E, name: String, clazz: Class[_]) = if (t != null) {
    t.value
  } else {
    null
  }
}
