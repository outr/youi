package org.hyperscala.examples.basic

import org.hyperscala.web.FormSupport
import org.hyperscala.html._
import attributes.{ButtonType, InputType}

import org.powerscala.property._
import tag._
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.web.module.jQuery172
import org.hyperscala.web.site.Webpage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class FormExample extends Webpage with FormSupport {
  title := "Form Example"

  require(jQuery172)

  val messages = new Div {
    style.padding.bottom := 10.px
  }
  body.contents += messages

  body.contents += new Form(id = "form", method = "post") {
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
    contents += new Button(id = "button1", buttonType = ButtonType.Submit, content = "Submit 1")
    contents += new Button(id = "button2", buttonType = ButtonType.Submit, content = "Submit 2")
  }
}
