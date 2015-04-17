package org.hyperscala

import org.powerscala.enum.EnumEntry

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait EnumEntryAttributeValue extends EnumEntry with AttributeValue {
  override def isMatch(s: String) = value.equalsIgnoreCase(s)

  override def equals(obj: scala.Any) = obj match {
    case av: AttributeValue => av.value == value
    case _ => false
  }
}
