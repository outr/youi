package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class PatternUnits(name: String) extends AttributeEntry[PatternUnits](name, parent = PatternUnits)

object PatternUnits extends AttributeObject[PatternUnits] {
  case object UserSpaceOnUse extends PatternUnits("userSpaceOnUse")
  case object ObjectBoundingBox extends PatternUnits("objectBoundingBox")

  val values = findValues.toVector
}