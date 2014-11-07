package org.hyperscala.examples.ui

import com.outr.net.http.session.MapSession
import org.hyperscala.bootstrap.component.Button
import org.hyperscala.css.Style
import org.hyperscala.css.attributes.{Display, FontStyle, FontWeight}
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.realtime._
import org.hyperscala.ui.ContentEditor
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ContentEditorExample(site: Website[MapSession]) extends Example {
  val boldButton = new Button(label = "Bold")
  val italicButton = new Button(label = "Italic")
  val redButton = new Button(label = "Red")
  val greenButton = new Button(label = "Green")
  val blueButton = new Button(label = "Blue")
  val blockButton = new Button(label = "Block")
  val colorInput = new tag.Input(id = "currentColor")

  val frame = new RealtimeFrame("/example/ui/content_editor.html", singleConnection = false) {
    style.width := 100.pct
    style.height := 300.px
    style.border := "none"
    style.outline := "none"
  }
  val editorPage = new EditorPage(site, this)
  frame.currentPage := editorPage

  contents += frame

  val controls = new tag.Div {
    contents += boldButton
    contents += italicButton
    contents += redButton
    contents += greenButton
    contents += blueButton
    contents += blockButton
    contents += new Button(label = "Insert Kitty") {
      mouseDownEvent.onRealtime {
        case evt => {
          editorPage.editor.insert(new tag.Img(src = "http://www.freesmileys.org/emoticons/emoticon-animal-038.gif"))
        }
      }
    }
    contents += new Button(label = "Unindent") {
      mouseDownEvent.onRealtime {
        case evt => {
          editorPage.editor.adjustStyleSize(Style.marginLeft, -16, min = 0)
        }
      }
    }
    contents += new Button(label = "Indent") {
      mouseDownEvent.onRealtime {
        case evt => {
          editorPage.editor.setStyle(Style.display, Display.Block)
          editorPage.editor.adjustStyleSize(Style.marginLeft, 16)
        }
      }
    }
    contents += new Button(label = "Decrease Size") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.adjustStyleSize(Style.fontSize, -1)
      }
    }
    contents += new Button(label = "Increase Size") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.adjustStyleSize(Style.fontSize, 1)
      }
    }
    contents += colorInput
  }
  contents += controls
}

class EditorPage(site: Website[MapSession], example: ContentEditorExample) extends Webpage[MapSession](site) {
  require(ContentEditor)

  val editableStyle = head.selector("""[contenteditable="true"]:active, [contenteditable="true"]:focus""")
  editableStyle.border := "none"
  editableStyle.outline := "none"

  val div = new tag.Div(id = "editor") {
    contentEditable := ContentEditable.True
    contents += new tag.P(content = "This is an example of the <span class=\"stylized\" style=\"color: red;\">ContentEditor</span> module functionality to assist editing content in-page.")
    contents += new tag.P(content = "Select a portion of this text and try the controls on the bottom of the page.")
  }
  val editor = ContentEditor(div)
  editor.bindInput(Style.color, example.colorInput)
  editor.bindToggle(Style.fontWeight, example.boldButton, List(FontWeight.Bold, FontWeight.Weight700), activeClass = Some("active"))
  editor.bindToggle(Style.fontStyle, example.italicButton, List(FontStyle.Italic), activeClass = Some("active"))
  editor.bindToggle(Style.color, example.redButton, List("#ff0000", "rgb(255, 0, 0)", "red"), activeClass = Some("active"))
  editor.bindToggle(Style.color, example.greenButton, List("#00ff00", "rgb(0, 255, 0)", "green"), activeClass = Some("active"))
  editor.bindToggle(Style.color, example.blueButton, List("#0000ff", "rgb(0, 0, 255)", "blue"), activeClass = Some("active"))
  editor.bindToggle(Style.display, example.blockButton, List(Display.Block), activeClass = Some("active"))
  editor.contentChanged.on {
    case evt => {
      println(div.outputString)
    }
  }
  body.contents += div
}