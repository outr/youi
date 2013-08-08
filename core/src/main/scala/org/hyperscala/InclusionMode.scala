package org.hyperscala

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class InclusionMode protected() extends EnumEntry

object InclusionMode extends Enumerated[InclusionMode] {
  val NotEmpty = new InclusionMode
  val Exclude = new InclusionMode
  val Always = new InclusionMode
  val Modified = new InclusionMode
  val ModifiedAndNotEmpty = new InclusionMode
}
