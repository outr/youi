package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Well(wellType: WellType = WellType.Default) extends tag.Div(clazz = List("well", wellType.className))

class WellType(val className: String) extends EnumEntry

object WellType extends Enumerated[WellType] {
  val Default = new WellType("well-default")
  val Small = new WellType("well-sm")
  val Larger = new WellType("well-lg")
}