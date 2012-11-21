package org.hyperscala.ui.widgets.visual

import org.powerscala.property.{PropertyParent, StandardProperty}
import org.powerscala.event.Listenable

import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Visual[T] extends tag.Div with PropertyParent with Listenable with Stringify[T] {
  def manifest: Manifest[T]

  val property = new StandardProperty[T]("property")(this, manifest)

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
}