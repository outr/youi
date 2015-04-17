package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Horizontal extends EnumEntry

object Horizontal extends Enumerated[Horizontal] {
  case object Left extends Horizontal
  case object Center extends Horizontal
  case object Right extends Horizontal

  val values = findValues.toVector
}