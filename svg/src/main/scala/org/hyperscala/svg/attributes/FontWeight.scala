package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class FontWeight(v: String = null) extends AttributeEntry[FontWeight](v, parent = FontWeight)

object FontWeight extends AttributeObject[FontWeight] {
  case object Normal extends FontWeight
  case object Bold extends FontWeight
  case object Bolder extends FontWeight
  case object Lighter extends FontWeight
  case object W100 extends FontWeight("100")
  case object W200 extends FontWeight("200")
  case object W300 extends FontWeight("300")
  case object W400 extends FontWeight("400")
  case object W500 extends FontWeight("500")
  case object W600 extends FontWeight("600")
  case object W700 extends FontWeight("700")
  case object W800 extends FontWeight("800")
  case object W900 extends FontWeight("900")
  case object Inherit extends FontWeight

  val values = findValues.toVector
}