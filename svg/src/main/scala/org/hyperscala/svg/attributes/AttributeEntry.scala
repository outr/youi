package org.hyperscala.svg.attributes

import org.powerscala.enum.EnumEntry
import org.hyperscala.{EnumEntryAttributeValue, AttributeValue}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class AttributeEntry[E <: EnumEntry with AttributeValue](v: String = null, parent: AttributeObject[E]) extends EnumEntryAttributeValue {
  lazy val value = v match {
    case null => generateValue()
    case _ => v
  }

  protected def generateValue() = {
    val b = new StringBuilder
    name.foreach {
      case c if c.isUpper => {
        if (b.length > 0) {
          b.append('-')
        }
        b.append(c.toLower)
      }
      case c => b.append(c)
    }
    b.toString()
  }
}