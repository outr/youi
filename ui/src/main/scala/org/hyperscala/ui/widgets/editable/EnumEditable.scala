package org.hyperscala.ui.widgets.editable

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class EnumEditable[E <: EnumEntry[E]](enumerated: Enumerated[E], initialValue: E) extends Editable[E, tag.Select](initialValue) {
  val editor = new tag.Select {
    contents.addAll(enumerated.values.map(e => new tag.Option(value = e.name(), content = e.toString())): _*)
  }
  editor.selected := List(toString(initialValue))

  def toString(value: E) = value match {
    case null => null
    case _ => value.name()
  }

  def fromString(s: String) = enumerated.apply(s)

  override def visualize(value: E) = value match {
    case null => null
    case _ => value.toString()
  }
}
