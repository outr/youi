package org.hyperscala.ui.widgets.editable

import org.hyperscala.html._
import constraints.BodyChild
import org.powerscala.property._
import backing.{VariableBacking, Backing}
import org.hyperscala.css.attributes.Display
import org.hyperscala.web.live.LiveEvent

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
abstract class Editable[T, I <: FormField](initialValue: T) extends tag.Div {
  /**
   * Defines the type of backing for this editable property.
   */
  def backing: Backing[T] = new VariableBacking[T]
  /**
   * Object representation of this editable. This is the value currently representing the display.
   */
  val property = Property[T]("property", initialValue, backing)
  /**
   * The current state of editing.
   *
   * Defaults to false.
   */
  val editing = Property[Boolean]("editing", false)
  /**
   * Defines whether changes should be immediately applied from the editor back to the display.
   *
   * Defaults to true.
   */
  val synchronized = Property[Boolean]("synchronized", true)

  /**
   * The editor component to use.
   */
  def editor: I

  /**
   * Defines how to convert T to a String for representation in the editor.
   */
  def toString(value: T): String

  /**
   * Defines how to convert String to a T when the editor is modified.
   */
  def fromString(s: String): T

  /**
   * Defines how to convert T to a non-editable visualization BodyChild.
   *
   * Defaults to calling toString(value).
   */
  def visualize(value: T): BodyChild = toString(value)

  /**
   * Represents the content for displaying non-editing content.
   */
  val display = new tag.Div

  editing.onChange {
    update()
  }
  property.onChange {
    updateAll()
  }

  override protected def initialize() {
    super.initialize()

    editor.event.change := LiveEvent()
    editor.value.onChange {
      if (synchronized()) {
        applyEditor()
      }
    }

    contents += display
    contents += editor

    updateAll()
  }

  /**
   * Updates the current state of the editable as well as applying the current value of 'property' to the editor and to
   * the non-editable display.
   */
  def updateAll() = {
    updateDisplay(visualize(property()))
    editor.value := toString(property())

    update()
  }

  /**
   * Updates the current state of the editable.
   */
  def update() = {
    editing() match {
      case true => {
        display.style.display := Display.None
        editor.style.display := Display.Block
      }
      case false => {
        display.style.display := Display.Block
        editor.style.display := Display.None
      }
    }
  }

  /**
   * Updates the non-editable display with the String representation.
   */
  def updateDisplay(child: BodyChild) = {
    display.contents.clear()
    if (child != null) {
      display.contents += child
    }
  }

  /**
   * Applies the current value in the editor to the property and thus the non-editable display.
   *
   * This is also used onchange when synchronized is set to true.
   */
  def applyEditor() = {
    property := fromString(editor.value())
  }
}
