package org.hyperscala.examples.basic

import org.hyperscala.web.FormSupport
import org.hyperscala.html._
import attributes.{ButtonType, InputType}

import org.powerscala.property._
import tag._
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.web.module.jQuery172
import org.hyperscala.web.site.realtime.RealtimeWebpage
import org.hyperscala.event.{ClickEvent, KeyUpEvent, ChangeEvent, JavaScriptEvent}
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class FormExample extends RealtimeWebpage with FormSupport {
  title := "Form Example"

  require(jQuery172)

  val messages = new Div {
    style.padding.bottom := 10.px
  }
  body.contents += messages

  body.contents += new Form(id = "form", method = "post") {
//    submittedBy.listeners.synchronous {
//      case evt: PropertyChangeEvent => {
//        messages.contents += "Form submitted by: %s".format(evt.newValue)
//        messages.contents += new Br
//      }
//    }

    val items = List("Name", "Phone", "Email")
    items.foreach {
      case item => {
        contents += new Div {
          contents += new Label(content = item)
          val input = new Input(name = item, inputType = InputType.Text) {
            event.keyUp := JavaScriptEvent(fireChange = true)

            listeners.synchronous {
              case evt: KeyUpEvent => println("KeyUp: %s".format(evt))
              case evt: ChangeEvent => println("Input ChangeEvent: %s".format(value()))
              case evt => println("Unhandled input event: %s".format(evt))
            }
            value.listeners.synchronous {
              case evt: PropertyChangeEvent => println("Value changed: %s to %s".format(evt.oldValue, evt.newValue))
            }
          }
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
    contents += new Button(id = "button1", buttonType = ButtonType.Submit, content = "Submit 1") {
      event.click := JavaScriptEvent()

      listeners.synchronous {
        case evt: ClickEvent => {
          val input = html.byName[tag.Input]("Name").head
          input.value := "Wahoo!"
          input.style.color := Color.Red
        }
      }
    }
    contents += new Button(id = "button2", buttonType = ButtonType.Submit, content = "Submit 2")
  }
}
