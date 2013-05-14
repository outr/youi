package org.hyperscala.ui.widgets

import org.hyperscala.html._
import attributes.InputType
import constraints.BodyChild
import org.powerscala.property.Property
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime
import org.hyperscala.event.JavaScriptEvent

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class MultiSelect[T](implicit manifest: Manifest[T]) extends tag.Div {
  Webpage().require(Realtime)

  val selected = Property[List[T]](default = Some(Nil))

  def isSelected(t: T) = selected().contains(t)

  def availableValues: List[T]

  def value(t: T): String
  def label(t: T): BodyChild

  override protected def initialize() {
    super.initialize()

    refresh()
    selected.change.on {
      case evt => updateUIFromSelected()
    }
  }

  def createEntry(t: T, checked: Boolean): Selectable[T] = new DefaultSelectable(this, t, checked)

  def insertEntries(entries: List[Selectable[T]]) = {
    entries.foreach {
      case e => contents += e
    }
  }

  def refresh() = {
    contents.clear()

    val entries = availableValues.collect {
      case t => {
        val entry = createEntry(t, isSelected(t))
        entry
      }
    }
    insertEntries(entries)
  }

  def updateUIFromSelected() = {
    byTag[Selectable[T]].foreach {
      case s => s.selected = isSelected(s.value)
    }
  }

  protected[ui] def updateSelectedFromUI() = {
    selected := byTag[Selectable[T]].collect {
      case s if (s.selected) => s.value
    }.toList
  }
}

trait Selectable[T] extends BodyChild {
  def value: T
  def selected: Boolean
  def selected_=(b: Boolean): Unit
}

class DefaultSelectable[T](multiSelect: MultiSelect[T], val value: T, checked: Boolean) extends tag.Label with Selectable[T] {
  val input = new tag.Input(inputType = InputType.CheckBox, value = multiSelect.value(value), checked = checked) {
    changeEvent := JavaScriptEvent()

    checked.change.on {
      case evt => multiSelect.updateSelectedFromUI()
    }
  }
  contents += input
  contents += "&#160;"
  contents += multiSelect.label(value)

  def selected = input.checked()

  def selected_=(b: Boolean) = input.checked := b
}