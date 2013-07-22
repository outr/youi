package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.ui.widgets.RichEditor

import org.hyperscala.event.JavaScriptEvent
import org.powerscala.Color
import org.hyperscala.examples.Example
import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RichEditorExample extends Example {
  val editor = new RichEditor {
    style.width := 500.px
    content.change.on {
      case evt => println("Event: %s / %s".format(evt, content()))
    }
  }
  val modify = new tag.Button(content = "Modify") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        editor.content := new tag.P {
          contents += new tag.B(content = "This")
          contents += " is "
          contents += new tag.I(content = "testing") {
            style.color := Color.Red
          }
          contents += " content!"
        }.outputString
      }
    }
  }
  val check = new tag.Button(content = "Check") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        println("Editor Content: %s".format(editor.content()))
      }
    }
  }

  contents += editor
  contents += modify
  contents += check
}
