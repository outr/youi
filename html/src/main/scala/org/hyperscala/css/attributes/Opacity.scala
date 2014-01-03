package org.hyperscala.css.attributes

import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Opacity(val n: Double) extends AttributeValue with NumericValue {
  def value = n.toString
}

object Opacity extends ValuePersistence[Opacity] {
  def apply(v: Double) = new Opacity(v)

  def fromString(s: String, name: String, clazz: Class[_]) = try {
    apply(s.toDouble)
  } catch {
    case t: Throwable => null
  }

  def toString(t: Opacity, name: String, clazz: Class[_]) = if (t != null) t.value else null
}