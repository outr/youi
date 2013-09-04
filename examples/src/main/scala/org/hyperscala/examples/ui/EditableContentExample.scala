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
    contents += ", "
    contents += new tag.A(href = "http://www.hyperscala.org", content = "a Link")
    contents += ", and Normal text."
  }
  val editable = EditableContent(div)
  editable.realtimeSelection := true
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
  contents += new tag.Button(content = "Dump Info") {
    clickEvent := JavaScriptEvent()
    clickEvent.on {
      case evt => {
        println(s"Text: ${editable.selectedText}")
        println(s"HTML: ${editable.selectedHTML}")
        println(s"Offset: ${editable.selectedStartOffset} - ${editable.selectedEndOffset}")
        println(s"Back Color: ${editable.backColor}")
        println(s"Bold: ${editable.isBold}")
        println(s"Link: ${editable.link}")
        println(s"Font Name: ${editable.fontName}")
        println(s"Font Size: ${editable.fontSize}")
        println(s"Fore Color: ${editable.foreColor}")
        println(s"Highlight Color: ${editable.hiliteColor}")
        println(s"Italic: ${editable.isItalic}")
        println(s"Justify Center: ${editable.isJustifyCenter}")
        println(s"Justify Full: ${editable.isJustifyFull}")
        println(s"Justify Left: ${editable.isJustifyLeft}")
        println(s"Justify Right: ${editable.isJustifyRight}")
        println(s"Strike-Through: ${editable.isStrikeThrough}")
        println(s"Subscript: ${editable.isSubscript}")
        println(s"Superscript: ${editable.isSuperscript}")
        println(s"Underline: ${editable.isUnderline}")
      }
    }
  }
}
