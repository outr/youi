package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Horizontal private() extends EnumEntry

object Horizontal extends Enumerated[Horizontal] {
  val Left = new Horizontal
  val Center = new Horizontal
  val Right = new Horizontal
}