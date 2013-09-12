package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.ui.widgets.RichEditor

import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.realtime.dsl._
import org.hyperscala.selector.Selector
import org.hyperscala.css.attributes.Position

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RichEditorExample extends Example {
  val activeStyle = page.head.selector(Selector.clazz("active"))
  activeStyle.backgroundColor := Color.Red

  val div = new tag.Div(id = "editable") {
    style.width := 500.px
    contents += new tag.H1(content = "Hello World!")
    contents += new tag.Br
    contents += new tag.Strong(content = "Some")
    contents += " text!"
  }

  val editor = RichEditor(div)
  editor.showToolbar := true
  editor.showPath := false
  editor.showResizer := false
  editor.showFormButtons(false)
  editor.onBold(addClass(Selector.id("boldToggle"), "active"))
  editor.onItalic(addClass(Selector.id("italicToggle"), "active"))
  editor.onUnderline(addClass(Selector.id("underlineToggle"), "active"))
  editor.onStrike(addClass(Selector.id("strikeToggle"), "active"))
  editor.onSuperscript(addClass(Selector.id("superscriptToggle"), "active"))
  editor.onSubscript(addClass(Selector.id("subscriptToggle"), "active"))
  editor.onFontSize(setValue(Selector.id("fontSize")))

  contents += div
  contents += new tag.Button(content = "Modify") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        editor.html := new tag.P {
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
  contents += new tag.Button(content = "Check") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        println("Editor Content: %s".format(editor.html()))
      }
    }
  }
  contents += new tag.Button(id = "boldToggle", content = "Bold") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => editor.bold()
    }
  }
  contents += new tag.Button(id = "italicToggle", content = "Italics") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => editor.italic()
    }
  }
  contents += new tag.Button(id = "underlineToggle", content = "Underline") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => editor.underline()
    }
  }
  contents += new tag.Button(id = "strikeToggle", content = "Strike") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => editor.strike()
    }
  }
  contents += new tag.Button(id = "superscriptToggle", content = "Superscript") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => editor.superscript()
    }
  }
  contents += new tag.Button(id = "subscriptToggle", content = "Subscript") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => editor.subscript()
    }
  }
  contents += new tag.Button(id = "reposition", content = "Reposition Editor") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        div.style.position := Position.Absolute
        div.style.left := 50.px
        div.style.top := 50.px
      }
    }
  }
  contents += new tag.Button(id = "toggleNewPage", content = "Toggle New Page Button") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        editor.showNewPageButton := !editor.showNewPageButton()
      }
    }
  }

  contents += new tag.Button(id = "setFontSize36", content = "Set Font Size to 36pt") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        editor.fontSize(36.pt)
      }
    }
  }
  contents += new tag.Button(id = "setFontSize72", content = "Set Font Size to 72pt") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        editor.fontSize(72.pt)
      }
    }
  }
  contents += new tag.Button(id = "setFontSizeNull", content = "Set Font Size to null") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => {
        editor.fontSize(null)
      }
    }
  }
  contents += new tag.Input(id = "fontSize")
}
