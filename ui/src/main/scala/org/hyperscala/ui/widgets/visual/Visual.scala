package org.hyperscala.ui.widgets.visual

import org.powerscala.property.Property
import org.powerscala.event.Listenable

import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Visual[T] extends tag.Div with Listenable with Stringify[T] {
  def manifest: Manifest[T]

  val property = new Property[T]()(this, manifest)

  def toString(t: T) = property() match {
    case null => ""
    case v => v.toString
  }
}

object Visual {
  def apply[T]()(implicit manifest: Manifest[T]) = VisualBuilder[T]()(manifest)

  def toggleEditing(container: HTMLTag) = container.byTag[EditableVisual[_]].foreach {
    case v => v.editing := !v.editing()
  }

  def editing(enabled: Boolean, container: HTMLTag) = container.byTag[EditableVisual[_]].foreach {
    case v => v.editing := enabled
  }
}