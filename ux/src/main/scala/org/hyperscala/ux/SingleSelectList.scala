package org.hyperscala.ux

import org.hyperscala.Container
import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.realtime._
import org.hyperscala.selector.Selector
import org.powerscala.property.{ListProperty, Property}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait SingleSelectList[T] {
  implicit def manifest: Manifest[T]

  val selected = Property[T]()
  val options = new Property[List[T]] with ListProperty[T]
  val disabled = Property[Boolean](default = Some(false))

  selected.change.on {
    case evt => refreshSelected()   // Refresh the selected display when the selected option changes
  }
  options.change.on {
    case evt => {
      refreshItems()                // Refresh the items list when the options list changes
      if (!options().contains(selected())) {
        selected := options().headOption.getOrElse(null).asInstanceOf[T]
      }
    }
  }

  def open(): Unit
  def close(): Unit
  def refreshItems(): Unit
  def refreshSelected(): Unit
}

class PredefinedSingleSelectList[T](val select: HTMLTag with Container[BodyChild], val drop: tag.Div)(implicit val manifest: Manifest[T], stringify: T => String) extends SingleSelectList[T] {
  // Create dropdown support
  val dropdown = Dropdown(select)
  dropdown.selector := Selector.id(drop.identity)

  def open() = dropdown.open()
  def close() = dropdown.close()
  def refreshItems() = {
    drop.contents.clear()
    options().foreach {
      case t => {
        val e = toElement(t)
        e.clickEvent.onRealtime {
          case evt => selected := t
        }
        drop.contents += e
      }
    }
  }
  def refreshSelected() = {
    select.contents.replaceWith(new tag.Span(content = stringify(selected())))
  }

  def toElement(t: T) = new tag.Div(clazz = List("listItem"), content = stringify(t))
}