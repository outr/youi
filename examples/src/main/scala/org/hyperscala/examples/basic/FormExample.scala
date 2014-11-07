package org.hyperscala.examples.basic

import com.outr.net.Method
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.html.attributes.{ButtonType, InputType}
import org.hyperscala.html.tag._
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FormExample extends Example {
  this.require(jQuery)

  val messages = new Div {
    style.paddingBottom := 10.px
  }
  contents += messages

  contents += new Form(id = "form", method = Method.Post) {
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
