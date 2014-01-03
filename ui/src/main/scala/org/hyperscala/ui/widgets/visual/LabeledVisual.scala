package org.hyperscala.ui.widgets.visual

import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait LabeledVisual {
  this: Visual[_] =>

  val label = new Property[String]()
}
