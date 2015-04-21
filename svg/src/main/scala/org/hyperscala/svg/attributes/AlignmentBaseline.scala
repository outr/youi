package org.hyperscala.svg.attributes


/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class AlignmentBaseline extends AttributeEntry[AlignmentBaseline](parent = AlignmentBaseline)

object AlignmentBaseline extends AttributeObject[AlignmentBaseline] {
  case object Auto extends AlignmentBaseline
  case object Baseline extends AlignmentBaseline
  case object BeforeEdge extends AlignmentBaseline
  case object TextBeforeEdge extends AlignmentBaseline
  case object Middle extends AlignmentBaseline
  case object Central extends AlignmentBaseline
  case object AfterEdge extends AlignmentBaseline
  case object TextAfterEdge extends AlignmentBaseline
  case object Ideographic extends AlignmentBaseline
  case object Alphabetic extends AlignmentBaseline
  case object Hanging extends AlignmentBaseline
  case object Mathematical extends AlignmentBaseline
  case object Inherit extends AlignmentBaseline

  val values = findValues.toVector
}