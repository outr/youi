package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Vertical private() extends EnumEntry

object Vertical extends Enumerated[Vertical] {
  val Top = new Vertical
  val Middle = new Vertical
  val Bottom = new Vertical
}