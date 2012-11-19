package org.hyperscala.examples.ui

import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.widgets.visual.Visualize
import org.powerscala.property.Property

import org.hyperscala.html._
import org.powerscala.property.event.PropertyChangeEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualizeExample extends Webpage {
  title := "Visualize Example"

  val instance = Property[Person]("person", Person("John Doe", "john@doeco.com", "Norman", "Oklahoma"))

  val messages = new tag.Div
  body.contents += messages

  val visualize = Visualize().clazz[Person](bindProperty = instance)
  body.contents += visualize.build()

  instance.listeners.synchronous {
    case evt: PropertyChangeEvent => {
      messages.contents += new tag.Div(content = "Person changed from %s to %s".format(evt.oldValue, evt.newValue))
    }
  }
}

case class Person(name: String = "", email: String = "", city: String = "", state: String = "")