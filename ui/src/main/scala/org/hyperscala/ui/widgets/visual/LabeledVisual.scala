package org.hyperscala.ui.widgets.visual

import org.powerscala.property.StandardProperty

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait LabeledVisual {
  this: Visual[_] =>

  val label = new StandardProperty[String]("label")
}
