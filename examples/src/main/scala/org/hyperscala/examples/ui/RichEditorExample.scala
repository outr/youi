package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.widgets.RichEditor

import org.powerscala.property._
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RichEditorExample extends Webpage {
  val editor = new RichEditor {
    style.width := 500.px
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
            style.color := Color.Red
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

  body.contents += editor
  body.contents += modify
  body.contents += check
}
