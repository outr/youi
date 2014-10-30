package org.hyperscala.examples.ui

import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.html._
import org.hyperscala.ui.ContentEditor
import org.hyperscala.web._
import org.hyperscala.examples.Example
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.realtime._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ContentEditorExample extends Example {
  require(ContentEditor)

  new SelectorStyleSheet("""[contenteditable="true"]:active, [contenteditable="true"]:focus""")(this) {
    border := "none"
    outline := "none"
  }

  val div = new tag.Div(id = "editor") {
    contentEditable := ContentEditable.True

    inputEvent.onRealtime {
      case evt => println("InputEvent!")
    }
    contents += new tag.P(content = "This is an example of the <span class=\"stylized\" style=\"color: red;\">ContentEditor</span> module functionality to assist editing content in-page.")
    contents += new tag.P(content = "Click the \"Start Editing\" button below to make this content editable.")
  }
  contents += div

  val controls = new tag.Div {
    contents += new tag.Button(content = "Selection Stats") {
      clickEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, """alert('Font-Weight: ' + selectionStyle('fontWeight') + ', Color: ' + selectionStyle('color'));""")
      }
    }
    contents += new tag.Button(content = "Bold") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('fontWeight', ['bold', '700'], null);")
      }
    }
    contents += new tag.Button(content = "Italics") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('fontStyle', ['italic'], null);")
      }
    }
    contents += new tag.Button(content = "Red") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('color', ['red', 'rgb(255, 0, 0)'], null);")
      }
    }
    contents += new tag.Button(content = "Green") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('color', ['green', 'rgb(0, 128, 0)'], null);")
      }
    }
    contents += new tag.Button(content = "Blue") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('color', ['blue', 'rgb(0, 0, 255)'], null);")
      }
    }
  }
  contents += controls
}