package org.hyperscala.examples.basic

import org.hyperscala.html._
import attributes.{ButtonType, InputType}

import tag._
import org.hyperscala.web._
import org.hyperscala.jquery.jQuery
import org.hyperscala.examples.Example
import com.outr.net.Method

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FormExample extends Example {
  this.require(jQuery.LatestWithDefault)

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
