package org.hyperscala.ui.widgets.visual

import org.powerscala.property.Property
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait EditableVisual[T] extends Visual[T] {
  val editing = new Property[Boolean]()

  def visualize(): BodyChild = toString(property())
}