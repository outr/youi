package org.hyperscala.ui.widgets.visual

import org.powerscala.property.StandardProperty
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait EditableVisual {
  this: Visual[_] =>

  val editing = new StandardProperty[Boolean]("editing")

  def visualize(): BodyChild = property().toString
}