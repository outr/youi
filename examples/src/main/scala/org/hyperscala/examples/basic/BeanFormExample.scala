package org.hyperscala.examples.basic

import org.hyperscala.web.{ActionForm, AJAXForm, HTMLPage}
import org.hyperscala.html._
import org.powerscala.property._
import org.hyperscala.bean.BeanForm
import org.powerscala.property.event.PropertyChangeEvent

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BeanFormExample extends HTMLPage {
  title := "BeanForm Example"

  head.contents += new Script(src = "/js/jquery-1.7.2.js")

  val messages = new Div {
    style.padding.bottom := 10.px
  }
  contents += messages

  val form = new BeanForm[Person]("person", Person("John Doe", "john@doe.com", "Some", "Where")) with AJAXForm with ActionForm {
    property.listeners.synchronous {
      case evt: PropertyChangeEvent => {
        messages.contents += "Person changed from %s to %s".format(evt.oldValue, evt.newValue)
        messages.contents += new Br
      }
    }
  }
  contents += form
}

case class Person(name: String = "", email: String = "", city: String = "", state: String = "")