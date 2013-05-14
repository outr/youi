package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.powerscala.property.{ListProperty, Property}
import visual.Stringify

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ListSelect[T](values: Seq[T], nullAllowed: Boolean = false)(implicit manifest: Manifest[T]) extends tag.Div with Stringify[T] {
  val select = new tag.Select

  def multiple = select.multiple

  val selected = new Property[List[T]](default = Some(Nil)) with ListProperty[T]
  private var changing = false
  selected.change.on {
    case evt => {
      if (!changing) {
        var break = false
        val selection = selected().map {
          case null if (!nullAllowed) => select.contents.headOption match {
            case Some(first) => toString(first.asInstanceOf[ListSelectItem[T]].item)
            case None => {
              break = true
              null
            } // Nothing we can do right now
          }
          case t => toString(t)
        }
        if (!break) {
          select.selected := selection
        }
      }
    }
  }
  select.selected.change.on {
    case evt => {
      changing = true
      try {
        selected := select.selectedOptions.map(o => o.asInstanceOf[ListSelectItem[T]].item).filterNot(t => t == null)
      } catch {
        case t: Throwable => {
          t.printStackTrace()
          select.contents.foreach(o => println("*** Option: %s".format(o.value())))
        }
      } finally {
        changing = false
      }
    }
  }

  contents += select

  if (nullAllowed) {
    this += null.asInstanceOf[T]
  }
  values.foreach {
    case v => this += v
  }

  def +=(item: T) = {
    select.contents += new ListSelectItem[T](item, this)
    if (!nullAllowed && selected().isEmpty) {
      selected := List(values.head)
    }
  }

  def -=(item: T) = {
    select.contents.find(o => o match {
      case lsi: ListSelectItem[_] if (lsi.item == item) => true
      case _ => false
    }) match {
      case Some(option) => select.contents -= option
      case None =>
    }
  }

  def nullString = "-- Select an Item --"

  def toString(item: T) = item match {
    case null => nullString
    case _ => String.valueOf(item)
  }
}

class ListSelectItem[T](val item: T, list: ListSelect[T]) extends tag.Option {
  value := list.toString(item)
  content := value()
}