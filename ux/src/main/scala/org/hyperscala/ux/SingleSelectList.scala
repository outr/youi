package org.hyperscala.ux

import org.hyperscala.Container
import org.hyperscala.event.Key
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
  val allowNull = Property[Boolean](default = Some(false))

  selected.change.on {
    case evt => refreshSelected()   // Refresh the selected display when the selected option changes
  }
  options.change.on {
    case evt => {
      refreshItems()                // Refresh the items list when the options list changes
      val default = (if (allowNull()) null else options().headOption.getOrElse(null)).asInstanceOf[T]
      if (!options().contains(selected())) {
        selected := default
      }
    }
  }

  def open(): Unit
  def close(): Unit
  def refreshItems(): Unit
  def refreshSelected(): Unit
}

abstract class DropdownSingleSelectList[Select <: HTMLTag, Type](val select: Select, val drop: tag.Div)(implicit val manifest: Manifest[Type]) extends SingleSelectList[Type] {
  // Create dropdown support
  val dropdown = Dropdown(select)
  dropdown.selector := Selector.id(drop.identity)

  def filter(list: List[Type]): List[Type] = list

  def open() = dropdown.open()
  def close() = dropdown.close()
  def refreshItems() = {
    drop.contents.clear()
    filter(options()).foreach {
      case t => {
        val e = toElement(t)
        e.clickEvent.onRealtime {
          case evt => selected := t
        }
        drop.contents += e
      }
    }
  }

  def toElement(t: Type): BodyChild
}

abstract class InputSingleSelectList[Type](select: tag.Input, drop: tag.Div)(implicit manifest: Manifest[Type]) extends DropdownSingleSelectList[tag.Input, Type](select, drop) {
  selected := fromString(select.value())
  select.keyUpEvent := RealtimeEvent(fireChange = true)
  select.keyUpEvent.on {
    case evt => if (evt.key == Key.Enter || evt.key == Key.Return) {
      validate()
    } else if (evt.key == Key.Escape) {
      select.value := toString(selected())
      close()
    } else {
      refreshItems()
      dropdown.open()
    }
  }
  select.blurEvent.onRealtime {
    case evt => validate()
  }

  def validate() = {
    selected := fromString(select.value())
    close()
  }

  override def toElement(t: Type) = new tag.Div(clazz = List("listItem"), content = toString(t))

  def toString(value: Type): String

  def fromString(s: String): Type
}

class PredefinedSingleSelectList[Select <: HTMLTag with Container[BodyChild], Type](select: Select, drop: tag.Div)(implicit manifest: Manifest[Type], stringify: Type => String) extends DropdownSingleSelectList[Select, Type](select, drop) {
  def refreshSelected() = {
    select.contents.replaceWith(toSelectedElement(selected()))
  }

  def toSelectedElement(t: Type): BodyChild = new tag.Span(content = stringify(selected()))

  def toElement(t: Type): BodyChild = new tag.Div(clazz = List("listItem"), content = stringify(t))
}