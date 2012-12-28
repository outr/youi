package org.hyperscala

import svg.attributes._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object svg {
  implicit val xmlSpacePersistence = XMLSpace
  implicit val alignmentBaselinePersistence = AlignmentBaseline
  implicit val fontStylePersistence = FontStyle
  implicit val fontVariantPersistence = FontVariant
  implicit val fontStretchPersistence = FontStretch
  implicit val fontWeightPersistence = FontWeight
  implicit val listTransformPersistence = ListTransformPersistence
}
