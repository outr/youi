package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class TextAnchor extends AttributeEntry[TextAnchor](parent = TextAnchor)

object TextAnchor extends AttributeObject[TextAnchor] {
  val Start = new TextAnchor
  val Middle = new TextAnchor
  val End = new TextAnchor
  val Inherit = new TextAnchor
}