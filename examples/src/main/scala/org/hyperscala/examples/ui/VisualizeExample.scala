package org.hyperscala.examples.ui

import org.hyperscala.ui.widgets.visual.Visualize
import org.powerscala.property.Property

import org.hyperscala.html._
import org.hyperscala.examples.Example

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualizeExample extends Example {
  val instance = Property[Person](default = Some(Person("John Doe", "john@doeco.com", "Norman", "Oklahoma")))

  val messages = new tag.Div
  contents += messages

  val visualize = Visualize().clazz[Person](bindProperty = instance)
  contents += visualize.build()

  instance.change.on {
    case evt => {
      messages.contents += new tag.Div(content = "Person changed from %s to %s".format(evt.oldValue, evt.newValue))
    }
  }
}

case class Person(name: String = "", email: String = "", city: String = "", state: String = "")