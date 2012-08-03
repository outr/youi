package org.hyperscala

import org.powerscala.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
sealed class InclusionMode extends EnumEntry[InclusionMode]

object InclusionMode extends Enumerated[InclusionMode] {
  val NotEmpty = new InclusionMode
  val Exclude = new InclusionMode
  val Always = new InclusionMode
  val Modified = new InclusionMode
}
