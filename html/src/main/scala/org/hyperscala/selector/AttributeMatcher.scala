package org.hyperscala.selector

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class AttributeMatcher(val value: String) extends EnumEntry

object AttributeMatcher extends Enumerated[AttributeMatcher] {
  /**
   * Value is exactly equal to the attribute value.
   */
  case object exactly extends AttributeMatcher("=")
  /**
   * Value contains this exact value in a space separated list.
   */
  case object contains extends AttributeMatcher("~=")
  /**
   * Value is exactly or begins with this followed immediately by '-'.
   */
  case object begins extends AttributeMatcher("|=")

  val values = findValues.toVector
}