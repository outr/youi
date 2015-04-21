package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class TextRendering extends AttributeEntry[TextRendering](parent = TextRendering)

object TextRendering extends AttributeObject[TextRendering] {
  case object Auto extends TextRendering
  case object OptimizeSpeed extends TextRendering
  case object OptimizeLegibility extends TextRendering
  case object GeometricPrecision extends TextRendering
  case object Inherit extends TextRendering

  val values = findValues.toVector
}