package org.hyperscala

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class InclusionMode extends EnumEntry

object InclusionMode extends Enumerated[InclusionMode] {
  case object NotEmpty extends InclusionMode
  case object Exclude extends InclusionMode
  case object Always extends InclusionMode
  case object Modified extends InclusionMode
  case object ModifiedAndNotEmpty extends InclusionMode

  val values = findValues.toVector
}
