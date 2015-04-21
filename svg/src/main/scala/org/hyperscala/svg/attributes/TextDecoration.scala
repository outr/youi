package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class TextDecoration extends AttributeEntry[TextDecoration](parent = TextDecoration)

object TextDecoration extends AttributeObject[TextDecoration] {
  case object None extends TextDecoration
  case object Underline extends TextDecoration
  case object Overline extends TextDecoration
  case object LineThrough extends TextDecoration
  case object Blink extends TextDecoration
  case object Inherit extends TextDecoration

  val values = findValues.toVector
}