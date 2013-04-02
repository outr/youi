package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.ui.widgets.RichEditor

import org.hyperscala.event.{ClickEvent, JavaScriptEvent}
import org.powerscala.Color
import org.hyperscala.examples.Example
import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RichEditorExample extends Example {
  val editor = new RichEditor {
    style.width = 500.px
    listeners.synchronous {
      case evt => println("Event: %s / %s".format(evt, content()))
    }
  }
  val modify = new tag.Button(content = "Modify") {
    event.click := JavaScriptEvent()

    listeners.synchronous {
      case evt: ClickEvent => {
        editor.content := new tag.P {
          contents += new tag.B(content = "This")
          contents += " is "
          contents += new tag.I(content = "testing") {
            style.color = Color.Red
          }
          contents += " content!"
        }.outputString
      }
    }
  }
  val check = new tag.Button(content = "Check") {
    event.click := JavaScriptEvent()

    listeners.synchronous {
      case evt: ClickEvent => {
        println("Editor Content: %s".format(editor.content()))
      }
    }
  }

  contents += editor
  contents += modify
  contents += check
}
