package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <matt@outr.com>
 */
class PatternUnits private(name: String) extends AttributeEntry[PatternUnits](name, parent = PatternUnits)

object PatternUnits extends AttributeObject[PatternUnits] {
  val UserSpaceOnUse = new PatternUnits("userSpaceOnUse")
  val ObjectBoundingBox = new PatternUnits("objectBoundingBox")
}