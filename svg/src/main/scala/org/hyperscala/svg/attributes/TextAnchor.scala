package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class TextAnchor extends AttributeEntry[TextAnchor](parent = TextAnchor)

object TextAnchor extends AttributeObject[TextAnchor] {
  case object Start extends TextAnchor
  case object Middle extends TextAnchor
  case object End extends TextAnchor
  case object Inherit extends TextAnchor

  val values = findValues.toVector
}