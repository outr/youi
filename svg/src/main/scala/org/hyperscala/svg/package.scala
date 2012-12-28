package org.hyperscala

import svg.attributes._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object svg {
  implicit val xmlSpacePersistence = XMLSpace
  implicit val alignmentBaseline = AlignmentBaseline
  implicit val fontStyle = FontStyle
  implicit val fontVariant = FontVariant
}
