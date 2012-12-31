package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class TextDecoration extends AttributeEntry[TextDecoration](parent = TextDecoration)

object TextDecoration extends AttributeObject[TextDecoration] {
  val None = new TextDecoration
  val Underline = new TextDecoration
  val Overline = new TextDecoration
  val LineThrough = new TextDecoration
  val Blink = new TextDecoration
  val Inherit = new TextDecoration
}