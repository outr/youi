package org.hyperscala.data

import org.hyperscala.html._
import org.powerscala.property.{ListProperty, StandardProperty}
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.editor.ValueEditor
import tag.{Select, Option}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListSelect[T](val property: StandardProperty[T],
                    identifier: T => String = (t: T) => t.toString, visualizer: T => String = (t: T) => t.toString)
                   (implicit manifest: Manifest[T]) extends Select with ValueEditor[T] {
  id := property.name()
  name := property.name()
  val values = new StandardProperty[List[T]]("values", Nil) with ListProperty[T]

  property.listeners.synchronous {
    case evt: PropertyChangeEvent => {
      contents.foreach {
        case beanOption: BeanOption[_] => beanOption.selected := (beanOption.t == evt.newValue)
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
      case v => contents += BeanOption(this, v, current == v, identifier, visualizer)
    }
    if (current == null && items.nonEmpty) {
      property := items.head
    }
  }
}

case class BeanOption[T](listSelect: ListSelect[T],
                         t: T,
                         currentlySelected: Boolean,
                         identifier: T => String,
                         visualizer: T => String) extends Option() {
  id := "%s%s".format(listSelect.id(), identifier(t))
  value := identifier(t)
  content := visualizer(t)
  if (currentlySelected) {
    selected := true
  }

  selected.listeners.synchronous {
    case evt: PropertyChangeEvent if (evt.newValue == true) => listSelect.property := t
  }
}