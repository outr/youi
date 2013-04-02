package org.hyperscala

import svg.attributes._
import org.powerscala.Color

import language.implicitConversions

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object svg {
  implicit val _xmlSpacePersistence = XMLSpace
  implicit val _alignmentBaselinePersistence = AlignmentBaseline
  implicit val _fontStylePersistence = FontStyle
  implicit val _fontVariantPersistence = FontVariant
  implicit val _fontStretchPersistence = FontStretch
  implicit val _fontWeightPersistence = FontWeight
  implicit val _listTransformPersistence = ListTransformPersistence
  implicit val _viewBoxPersistence = ViewBox
  implicit val _listPointPersistence = Point
  implicit val _paintPersistence = Paint
  implicit val _imageRenderingPersistence = ImageRendering
  implicit val _textAnchorPersistence = TextAnchor
  implicit val _textDecorationPersistence = TextDecoration
  implicit val _textRenderingPersistence = TextRendering
  implicit val _dominantBaselinePersistence = DominantBaseline

  implicit def color2Paint(color: Color) = Paint.Color(color)
}
