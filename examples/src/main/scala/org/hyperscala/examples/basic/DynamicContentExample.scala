package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.powerscala.property.Property
import org.hyperscala.ui.DynamicContent

import org.hyperscala.ui.binder._
import org.powerscala.property.event.PropertyChangeEvent
import org.powerscala.IO

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicContentExample extends Webpage {
  val form = new SimpleDynamicForm
  body.contents += form
}

class SimpleDynamicForm extends DynamicContent(null) {
  def content = SimpleDynamicForm.content

  val person = Property[Person]("person", Person("John Doe", 123))
  person.listeners.synchronous {
    case evt: PropertyChangeEvent => println("Person changed from %s to %s".format(evt.oldValue, evt.newValue))
  }

  val nameInput = bind[tag.Input, String]("i1", person, "name")
  val ageInput = bind[tag.Input, Int]("i2", person, "age")
  val button = load[tag.Button]("b1")

  button.contents.replaceWith("Do Something")
}

object SimpleDynamicForm {
  val content = IO.copy(getClass.getClassLoader.getResource("dynamic.html"))
}

case class Person(name: String, age: Int)