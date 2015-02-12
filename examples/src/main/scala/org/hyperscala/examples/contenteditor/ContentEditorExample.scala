package org.hyperscala.examples.contenteditor

import com.outr.net.http.session.MapSession
import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.bootstrap.component.Button
import org.hyperscala.contenteditor.ContentEditor
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.realtime._
import org.hyperscala.web.{Webpage, Website}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ContentEditorExample(site: Website[MapSession]) extends Example {
  require(Realtime)
  require(Bootstrap)

  val boldButton = new Button(label = "Bold") {
    clickEvent.onRealtime {
      case evt => editorPage.editor.toggleBold()
    }
  }
  val italicButton = new Button(label = "Italic")
  val redButton = new Button(label = "Red")
  val greenButton = new Button(label = "Green")
  val blueButton = new Button(label = "Blue")
  val colorInput = new tag.Input(id = "currentColor")
  val fontFamily = new tag.Input(id = "fontFamily")
  val fontStyle = new tag.Input(id = "fontStyle")
  val fontSize = new tag.Input(id = "fontSize")
  val leftAlign = new Button(label = "Left Align")
  val centerAlign = new Button(label = "Center Align")
  val rightAlign = new Button(label = "Right Align")
  val justifyAlign = new Button(label = "Justify Align")

  val frame = new RealtimeFrame("/example/wrapper/content_editor.html") {
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
    contents += new Button(label = "Insert Kitty") {
      mouseDownEvent.onRealtime {
        case evt => {
//          editorPage.editor.insert(new tag.Img(src = "http://www.freesmileys.org/emoticons/emoticon-animal-038.gif"))
        }
      }
    }
    contents += new Button(label = "Unindent") {
      mouseDownEvent.onRealtime {
        case evt =>
      }
    }
    contents += new Button(label = "Indent") {
      mouseDownEvent.onRealtime {
        case evt =>
      }
    }
    contents += new Button(label = "Decrease Size") {
      mouseDownEvent.onRealtime {
        case evt =>
      }
    }
    contents += new Button(label = "Increase Size") {
      mouseDownEvent.onRealtime {
        case evt =>
      }
    }
    contents += new Button(label = "Ordered List") {
      mouseDownEvent.onRealtime {
        case evt =>
      }
    }
    contents += new Button(label = "Unordered List") {
      mouseDownEvent.onRealtime {
        case evt =>
      }
    }
    contents += new Button(label = "Clear Formatting") {
      mouseDownEvent.onRealtime {
        case evt =>
      }
    }
    contents += colorInput
    contents += fontFamily
    contents += fontStyle
    contents += fontSize
    contents.addAll(leftAlign, centerAlign, rightAlign, justifyAlign)
  }
  contents += controls
}

class EditorPage(site: Website[MapSession], example: ContentEditorExample) extends Webpage[MapSession](site) {
  require(ContentEditor)

  val editableStyle = head.selector( """[contenteditable="true"]:active, [contenteditable="true"]:focus""")
  editableStyle.border := "none"
  editableStyle.outline := "none"
  val div = new tag.Div(id = "editor") {
    contentEditable := ContentEditable.True
    contents += new tag.P(content = "This is an example of the <span class=\"stylized\" style=\"color: red;\">ContentEditor</span> module functionality to assist editing content in-page.")
    contents += new tag.P(content = "Select a portion of this text and try the controls on the bottom of the page.")
  }
  val editor = ContentEditor(div)

  body.contents += div
}