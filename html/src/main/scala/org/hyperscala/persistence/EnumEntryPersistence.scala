package org.hyperscala.persistence

import org.powerscala.{Enumerated, EnumEntry}
import org.powerscala.reflect._
import org.hyperscala.html.attributes.AttributeValue

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait EnumEntryPersistence[E <: EnumEntry[_] with AttributeValue] extends ValuePersistence[E] {
  def fromString(s: String, clazz: Class[_]) = {
    val ec: EnhancedClass = clazz
    val companion = ec.companion.getOrElse(throw new RuntimeException("No companion for %s".format(clazz.getName))).instance.get
    companion.asInstanceOf[Enumerated[_]].values.find(e => e.asInstanceOf[AttributeValue].value == s).get.asInstanceOf[E]
  }

  def toString(t: E, clazz: Class[_]) = t.value
}
