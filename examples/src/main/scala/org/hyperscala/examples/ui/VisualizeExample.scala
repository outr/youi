package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.ui.widgets.visual.Visualize
import org.hyperscala.web.Webpage
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VisualizeExample extends Webpage with Example {
  val instance = Property[Person](default = Some(Person("John Doe", "john@doeco.com", "Norman", "Oklahoma")))

  val messages = new tag.Div
  body.contents += messages

  val visualize = Visualize().clazz[Person](bindProperty = instance)
  body.contents += visualize.build()

  instance.change.on {
    case evt => {
      messages.contents += new tag.Div(content = "Person changed from %s to %s".format(evt.oldValue, evt.newValue))
    }
  }
}

case class Person(name: String = "", email: String = "", city: String = "", state: String = "")