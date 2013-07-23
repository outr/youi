package org.hyperscala.css

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class AttributeMatcher(value: String) extends EnumEntry

object AttributeMatcher extends Enumerated[AttributeMatcher] {
  /**
   * Value is exactly equal to the attribute value.
   */
  val exactly = new AttributeMatcher("=")
  /**
   * Value contains this exact value in a space separated list.
   */
  val contains = new AttributeMatcher("~=")
  /**
   * Value is exactly or begins with this followed immediately by '-'.
   */
  val begins = new AttributeMatcher("|=")
}