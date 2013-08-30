package org.hyperscala.examples.ui

import org.hyperscala.html._

import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.ui.wrapped.EditableContent
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class EditableContentExample extends Example {
  val div = new tag.Div {
    contents += new tag.Strong(content = "Bold")
    contents += ", "
    contents += new tag.Em(content = "Italics")
    contents += ", and Normal text."
  }
  val editable = EditableContent(div)
  editable.contentChanged.on {
    case evt => info(s"Content changed to: ${evt.htmlString}")
  }

  contents += div
  contents += new tag.Button(content = "Toggle Bold") {
    clickEvent := JavaScriptEvent()
    clickEvent.on {
      case evt => editable.bold()
    }
  }
  contents += new tag.Button(content = "Random Background Color") {
    clickEvent := JavaScriptEvent()
    clickEvent.on {
      case evt => editable.backColor(Color.random)
    }
  }
}
