package org.hyperscala.examples.basic

import org.hyperscala.web.HTMLPage
import org.hyperscala.html._
import attributes.InputType
import org.powerscala.property.event.PropertyChangeEvent

import org.powerscala.property._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class FormExample extends HTMLPage {
  title := "Form Example"

  val messages = new Div {
    style.padding.bottom := 10.px
  }
  contents += messages

  contents += new Form(method = "post") {
    val items = List("Name", "Phone", "Email")
    items.foreach {
      case item => {
        contents += new Div {
          contents += new Label(content = item)
          val input = new Input(name = item, inputType = InputType.Text)
          input.value.listeners.synchronous {
            case evt: PropertyChangeEvent => {
              messages.contents += "Input changed (%s) from %s to %s".format(item, evt.oldValue, evt.newValue)
              messages.contents += new Br
            }
          }
          contents += input
        }
      }
    }
    contents += new Button(buttonType = "submit", content = "Submit")
  }
}
