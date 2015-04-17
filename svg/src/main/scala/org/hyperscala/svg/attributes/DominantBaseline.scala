package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class DominantBaseline extends AttributeEntry[DominantBaseline](parent = DominantBaseline)

object DominantBaseline extends AttributeObject[DominantBaseline] {
  case object Auto extends DominantBaseline
  case object UseScript extends DominantBaseline
  case object NoChange extends DominantBaseline
  case object ResetSize extends DominantBaseline
  case object Ideographic extends DominantBaseline
  case object Alphabetic extends DominantBaseline
  case object Hanging extends DominantBaseline
  case object Mathematical extends DominantBaseline
  case object Central extends DominantBaseline
  case object Middle extends DominantBaseline
  case object TextAfterEdge extends DominantBaseline
  case object TextBeforeEdge extends DominantBaseline
  case object Inherit extends DominantBaseline

  val values = findValues.toVector
}