package org.hyperscala.ui.widgets.visual

import org.powerscala.property.{PropertyParent, StandardProperty}
import org.powerscala.event.Listenable

import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Visual[T] extends tag.Div with PropertyParent with Listenable {
  def manifest: Manifest[T]

  val property = new StandardProperty[T]("property")(this, manifest)
}