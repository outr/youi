package org.hyperscala.ui.widgets.visual

import `type`.VisualType
import org.hyperscala.html._
import org.hyperscala.css.attributes._
import org.powerscala.property._
import org.hyperscala.Unique
import org.powerscala.Color
import tag.Text

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class StandardVisual[T](builder: VisualBuilder[T]) extends Visual[T]
                                                   with EditableVisual[T]
                                                   with LabeledVisual
                                                   with ValidatableVisual[T] {
  def manifest = builder.manifest

  name := builder.name

  property.change.on {                 // Update the read visualization on property change
    case evt => updateVisual()
  }

  editing.change.on {                  // Update the editing state on property change
    case evt => updateEditing()
  }

  builder.validations.foreach {       // Add builder validations
    case f => this += f
  }

  override def validateOnChange = builder.validateOnChange

  def hasError = errorContainer.contents.head.asInstanceOf[Text].content() != ""

  def errorMessage(message: String) = {
    if (message != null) {
      errorContainer.contents.replaceWith(message)
    } else {
      errorContainer.contents.replaceWith("")
    }
  }

  val errorContainer = new tag.Span {
    style.display = Display.Block
    style.color = Color.immutable("#c00")
    style.fontSize = FontSize.Percent(80)
    style.fontStyle = "normal"
    style.float = Float.Left
    style.paddingAll = 2.px
    style.paddingLeft = 5.px
  }
  val labelContainer = new tag.Label(content = builder.label) {
    style.float = Float.Left
    style.textAlign = Alignment.Right
    style.paddingRight = 5.px
    if (builder.isRequired) {
      contents += new tag.Div(content = "*") {
        style.float = Float.Right
        style.paddingLeft = 5.px
        style.color = Color.immutable("#c00")
      }
    }
  }
  val readContainer = new tag.Div {
    style.float = Float.Left
  }
  val editor = builder.visualType match {
    case Some(visualType) => visualType.create(property, builder)
    case None => VisualType(property, builder).getOrElse(throw new NullPointerException("No visual type for %s - %s".format(builder, builder.clazz)))
  }
  if (editor.id() == null) {
    editor.id := Unique()
  }
  if (editor.name() == null) {
    editor.name := editor.id()
  }
  labelContainer.forElement := editor.name()
  editor.style.float = Float.Left

  setup()

  // Optionally binds to a higher-level property using CaseClassBinding
  val binding = if (builder.bindProperty != null && builder.bindHierarchy != null) {    // Bind to another property
    val b = CaseClassBinding(builder.bindProperty, builder.bindHierarchy, property.asInstanceOf[Property[Any]], valueUpdatesProperty = builder.bindValueUpdatesProperty, propertyUpdatesValue = builder.bindPropertyUpdatesValue)
    if (builder.bindProperty() != null) {
      b.updateValueProperty()
    }
    Some(b)
  } else if (builder.bindProperty != null) {    // Directly bind to another property
    // TODO: we should make some mechanism of disconnecting
    if (builder.bindPropertyUpdatesValue) {
      property bind builder.bindProperty.asInstanceOf[Property[T]]
    }
    if (builder.bindValueUpdatesProperty) {
      builder.bindProperty.asInstanceOf[Property[T]] bind property
    }
    None
  } else {
    None
  }

  def setup() = {
    style.clear = Clear.Left

    if (builder.labeled) {
      contents += labelContainer
    }
    readContainer.contents.replaceWith(visualize())
    contents += readContainer
    if (builder.editable) {
      contents += editor
    }
    contents += errorContainer
    editing := builder.isEditing
    builder.default match {
      case Some(d) => property := d
      case None => // Leave it alone
    }
    Property.fireChanged(property)
    updateEditing()
  }

  def updateEditing() = {
    if (editing()) {
      readContainer.style.display = Display.None
      editor.style.display = Display.Block
    } else {
      readContainer.style.display = Display.Block
      editor.style.display = Display.None
    }
  }

  def updateVisual() = {
    readContainer.contents.replaceWith(visualize())
  }

  override def toString(t: T) = editor match {
    case stringify: Stringify[_] => stringify.asInstanceOf[Stringify[T]].toString(t)
    case _ => super.toString(t)
  }

  def dispose() = {     // TODO: make use of this method
    binding match {
      case Some(b) => b.disconnect()
      case None => // No binding to disconnect
    }
  }
}
