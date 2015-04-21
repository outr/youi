package org.hyperscala.svg.event

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class MouseButton extends EnumEntry

object MouseButton extends Enumerated[MouseButton] {
  case object Left extends MouseButton
  case object Middle extends MouseButton
  case object Right extends MouseButton

  val values = findValues.toVector
}