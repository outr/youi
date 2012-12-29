package org.hyperscala

import svg.attributes._
import org.powerscala.Color

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
  implicit val viewBoxPersistence = ViewBox
  implicit val listPointPersistence = Point
  implicit val paintPersistence = Paint

  implicit def color2Paint(color: Color) = Paint.Color(color)
}
