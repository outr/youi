package org.hyperscala.svg.event

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class MouseButton extends EnumEntry

object MouseButton extends Enumerated[MouseButton] {
  val Left = new MouseButton
  val Middle = new MouseButton
  val Right = new MouseButton
}