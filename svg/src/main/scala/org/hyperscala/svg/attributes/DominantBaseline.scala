package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class DominantBaseline extends AttributeEntry[DominantBaseline](parent = DominantBaseline)

object DominantBaseline extends AttributeObject[DominantBaseline] {
  val Auto = new DominantBaseline
  val UseScript = new DominantBaseline
  val NoChange = new DominantBaseline
  val ResetSize = new DominantBaseline
  val Ideographic = new DominantBaseline
  val Alphabetic = new DominantBaseline
  val Hanging = new DominantBaseline
  val Mathematical = new DominantBaseline
  val Central = new DominantBaseline
  val Middle = new DominantBaseline
  val TextAfterEdge = new DominantBaseline
  val TextBeforeEdge = new DominantBaseline
  val Inherit = new DominantBaseline
}