package org.hyperscala.examples.basic

import org.hyperscala.html._
import attributes.{ButtonType, InputType}

import tag._
import org.hyperscala.web.Webpage
import org.hyperscala.jquery.jQuery172
import org.hyperscala.examples.Example

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class FormExample extends Example {
  Webpage().require(jQuery172)

  val messages = new Div {
    style.paddingBottom := 10.px
  }
  contents += messages

  contents += new Form(id = "form", method = "post") {
    val items = List("Name", "Phone", "Email")
    items.foreach {
      case item => {
        contents += new Div {
          contents += new Label(content = item)
          val input = new Input(name = item, inputType = InputType.Text)
          input.value.change.on {
            case evt => {
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
