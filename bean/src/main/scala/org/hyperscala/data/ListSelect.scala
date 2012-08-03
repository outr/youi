package org.hyperscala.data

import org.hyperscala.html._
import org.powerscala.property.{ListProperty, StandardProperty}
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.editor.ValueEditor

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListSelect[T](val property: StandardProperty[T],
                    identifier: T => String = (t: T) => t.toString, visualizer: T => String = (t: T) => t.toString)
                   (implicit manifest: Manifest[T]) extends Select with ValueEditor[T] {
  name := property.name()
  val values = new StandardProperty[List[T]]("values", Nil) with ListProperty[T]

  property.listeners.synchronous {
    case evt: PropertyChangeEvent => {
      if (evt.oldValue != null) {
        contents.collectFirst {
          case beanOption: BeanOption[_] if (beanOption.t == evt.oldValue) => beanOption.asInstanceOf[BeanOption[T]]
        } match {
          case Some(beanOption) => beanOption.selected := false
          case None => // Can't find it in the list, may have been removed
        }
      }
      if (evt.newValue != null) {
        contents.collectFirst {
          case beanOption: BeanOption[_] if (beanOption.t == evt.newValue) => beanOption.asInstanceOf[BeanOption[T]]
        } match {
          case Some(beanOption) => beanOption.selected := true
          case None => // Can't find it in the list, may have been removed
        }
      }
    }
  }

  values.onChange {
    refreshOptions()
  }

  private def refreshOptions() = {
    contents.clear()
    val items = values()
    val current = property()
    items.foreach {
      case value => contents += BeanOption(this, value, current == value, identifier, visualizer)
    }
    if (current == null && items.nonEmpty) {
      property := items.head
    }
  }
}

case class BeanOption[T](select: ListSelect[T],
                         t: T, currentlySelected: Boolean,
                         identifier: T => String,
                         visualizer: T => String) extends Option {
  value := identifier(t)
  contents += visualizer(t)
  if (currentlySelected) {
    selected := true
  }

  selected.listeners.synchronous {
    case evt: PropertyChangeEvent if (evt.newValue == true) => select.property := t
  }
}