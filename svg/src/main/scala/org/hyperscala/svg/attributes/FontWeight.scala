package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class FontWeight(v: String = null) extends AttributeEntry[FontWeight](v, parent = FontWeight)

object FontWeight extends AttributeObject[FontWeight] {
  val Normal = new FontWeight
  val Bold = new FontWeight
  val Bolder = new FontWeight
  val Lighter = new FontWeight
  val W100 = new FontWeight("100")
  val W200 = new FontWeight("200")
  val W300 = new FontWeight("300")
  val W400 = new FontWeight("400")
  val W500 = new FontWeight("500")
  val W600 = new FontWeight("600")
  val W700 = new FontWeight("700")
  val W800 = new FontWeight("800")
  val W900 = new FontWeight("900")
  val Inherit = new FontWeight
}