package org.hyperscala.svg.attributes


/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class AlignmentBaseline extends AttributeEntry[AlignmentBaseline]

object AlignmentBaseline extends AttributeObject[AlignmentBaseline] {
  val Auto = new AlignmentBaseline
  val Baseline = new AlignmentBaseline
  val BeforeEdge = new AlignmentBaseline
  val TextBeforeEdge = new AlignmentBaseline
  val Middle = new AlignmentBaseline
  val Central = new AlignmentBaseline
  val AfterEdge = new AlignmentBaseline
  val TextAfterEdge = new AlignmentBaseline
  val Ideographic = new AlignmentBaseline
  val Alphabetic = new AlignmentBaseline
  val Hanging = new AlignmentBaseline
  val Mathematical = new AlignmentBaseline
  val Inherit = new AlignmentBaseline
}