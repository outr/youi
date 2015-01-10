package org.hyperscala.ui.wrapped

import org.hyperscala.html._
import org.hyperscala.realtime.RealtimeEvent
import org.powerscala.Unique
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class TypedSelect[T](val select: tag.Select)(implicit manifest: Manifest[T]) {
  select.contents.clear()                     // Start with an empty select list
  if (select.changeEvent() == null) {         // Make sure we're able to receive changes
    select.changeEvent := RealtimeEvent()
  }

  val selected = Property[T]()
  selected.change.on {
    case evt => select.selectedOptions := typedOptions.find(o => o.t == evt.newValue).toList
  }
  select.selectedOptions.change.on {
    case evt => selected := evt.newValue.headOption.map(o => o.asInstanceOf[TypedOption[T]].t).getOrElse(null.asInstanceOf[T])
  }

  def t2String(t: T): String

  def typedOptions = select.byTag[TypedOption[T]]
  def options = typedOptions.map(o => o.t).toList
  def options_=(options: Seq[T]) = {
    val s = selected()
    select.contents.replaceWith(options.map(t => new TypedOption(t, this)): _*)
    selected := s
  }
}

object TypedSelect {
  def apply[T](select: tag.Select, values: Seq[T])(f: T => String)(implicit manifest: Manifest[T]) = new TypedSelect[T](select) {
    options = values

    def t2String(t: T) = f(t)
  }
}

class TypedOption[T](val t: T, select: TypedSelect[T]) extends tag.Option(value = select.t2String(t), content = select.t2String(t)) {
  override def toString = s"TypedOption($t)"
}