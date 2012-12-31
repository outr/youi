package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class TextRendering extends AttributeEntry[TextRendering](parent = TextRendering)

object TextRendering extends AttributeObject[TextRendering] {
  val Auto = new TextRendering
  val OptimizeSpeed = new TextRendering
  val OptimizeLegibility = new TextRendering
  val GeometricPrecision = new TextRendering
  val Inherit = new TextRendering
}