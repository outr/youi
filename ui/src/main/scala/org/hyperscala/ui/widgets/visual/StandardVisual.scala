package org.hyperscala.ui.widgets.visual

import `type`.VisualType
import org.hyperscala.html._
import org.hyperscala.css.attributes.Display
import org.powerscala.property._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class StandardVisual[T](builder: VisualBuilder[T]) extends Visual[T]
                                                   with EditableVisual
                                                   with LabeledVisual
                                                   with ValidatableVisual[T] {
  def manifest = builder.manifest

  property.onChange {                 // Update the read visualization on property change
    updateVisual()
  }

  editing.onChange {                  // Update the editing state on property change
    updateEditing()
  }

  builder.validations.foreach {       // Add builder validations
    case f => this += f
  }

  // Optionally binds to a higher-level property using CaseClassBinding
  val binding = if (builder.bindProperty != null && builder.bindHierarchy != null) {    // Bind to another property
    val b = CaseClassBinding(builder.bindProperty, builder.bindHierarchy, property.asInstanceOf[StandardProperty[Any]])
    b.updateValueProperty()
    Some(b)
  } else {
    None
  }

  val labelDiv = new tag.Div
  val readDiv = new tag.Div
  val editableDiv = new tag.Div
  val editor = builder.visualType match {
    case Some(visualType) => visualType.create(property, builder)
    case None => VisualType(property, builder).getOrElse(throw new NullPointerException("No visual type for %s".format(builder)))
  }

  setup()

  def setup() = {
    if (builder.labeled) {
      labelDiv.contents.replaceWith(builder.label)
      contents += labelDiv
    }
    readDiv.contents.replaceWith(visualize())
    contents += readDiv
    if (builder.editable) {
      editableDiv.contents.replaceWith(editor)
      contents += editableDiv
    }
    editing := builder.isEditing
    builder.default match {
      case Some(d) => property := d
      case None => // Leave it alone
    }
    updateEditing()
  }

  def updateEditing() = {
    if (editing()) {
      readDiv.style.display := Display.None
      editableDiv.style.display := Display.Block
    } else {
      readDiv.style.display := Display.Block
      editableDiv.style.display := Display.None
    }
  }

  def updateVisual() = {
    readDiv.contents.replaceWith(visualize())
  }

  def dispose() = {     // TODO: make use of this method
    binding match {
      case Some(b) => b.disconnect()
      case None => // No binding to disconnect
    }
  }
}
