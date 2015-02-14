package org.hyperscala.examples.contenteditor

import com.outr.net.http.session.MapSession
import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.bootstrap.component.Button
import org.hyperscala.contenteditor.ContentEditor
import org.hyperscala.css.Style
import org.hyperscala.css.attributes.{FontStyle, FontWeight}
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.javascript.dsl.{parent, document}
import org.hyperscala.realtime._
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ContentEditorExample(site: Website[MapSession]) extends Example {
  require(Realtime)
  require(Bootstrap)

  val boldButton = styleToggleButton("Bold", Style.fontWeight, FontWeight.Bold)
  val italicButton = styleToggleButton("Italic", Style.fontStyle, FontStyle.Italic)
  val redButton = styleSetButton("Red", Style.color, Color.Red)
  val greenButton = styleSetButton("Green", Style.color, Color.Green)
  val blueButton = styleSetButton("Blue", Style.color, Color.Blue)
  val colorInput = new tag.Input(id = "currentColor")
  val fontFamily = new tag.Input(id = "fontFamily")
  val fontStyle = new tag.Input(id = "fontStyle")
  val fontSize = new tag.Input(id = "fontSize")
  val leftAlign = new Button(label = "Left Align")
  val centerAlign = new Button(label = "Center Align")
  val rightAlign = new Button(label = "Right Align")
  val justifyAlign = new Button(label = "Justify Align")

  val frame = new RealtimeFrame("/example/wrapper/content_editor_content.html") {
    style.width := 100.pct
    style.height := 300.px
    style.border := "none"
    style.outline := "none"
  }
  val editorPage = new EditablePageExample(this, site)
  frame.currentPage := editorPage
  contents += frame

  def styleToggleButton[T, S <: Style[T]](label: String, style: S, value: T) = {
    val b = new Button(label = label)
    b.clickEvent.onRealtime {
      case evt => editorPage.editor.toggle(style, value)
    }
    b
  }

  def styleSetButton[T, S <: Style[T]](label: String, style: S, value: T) = {
    val b = new Button(label = label)
    b.clickEvent.onRealtime {
      case evt => editorPage.editor.set(style, value)
    }
    b
  }

  val controls = new tag.Div {
    contents += boldButton
    contents += italicButton
    contents += redButton
    contents += greenButton
    contents += blueButton
    contents += new Button(label = "Insert Kitty") {
      mouseDownEvent.onRealtime {
        case evt => {
          editorPage.editor.insertImage("http://www.freesmileys.org/emoticons/emoticon-animal-038.gif")
        }
      }
    }
    contents += new Button(label = "Unindent") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.unIndent()
      }
    }
    contents += new Button(label = "Indent") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.indent()
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

class EditablePageExample(example: ContentEditorExample, site: Website[MapSession]) extends Webpage[MapSession](site) {
  require(ContentEditor)

  val editableStyle = head.selector( """[contenteditable="true"]:active, [contenteditable="true"]:focus""")
  editableStyle.border := "none"
  editableStyle.outline := "none"
  val div = new tag.Div(id = "editor") {
    contentEditable := ContentEditable.True
    contents += new tag.P(content = "This is an example of the <span class=\"stylized-color\" style=\"color: red;\">ContentEditor</span> module functionality to assist editing content in-page.")
    contents += new tag.P(content = "Select a portion of this text and try the controls on the bottom of the page.")
  }
  val editor = ContentEditor(div)

  body.contents += div

  if (example != null) {
    editor.bind(example.colorInput, Style.color, parent)
  }

  body.contents += new Button("Test Red") {
    id := "testButton1"
  }

  body.contents += new Button("Test Green") {
    id := "testButton2"
  }

  head.contents += new tag.Script {
    contents += new JavaScriptString(
      """
        |window.onload = function() {
        | var fmtColor = contentEditor.createClassWrapper("stylized-color");
        | document.getElementById('testButton1').onclick = function(evt) {
        |  fmtColor.undo();
        |  fmtColor.style = { color: '#ff0000' };
        |  fmtColor.apply()
        | };
        |
        | document.getElementById('testButton2').onclick = function(evt) {
        |  fmtColor.undo();
        |  fmtColor.style = { color: 'green' };
        |  fmtColor.apply()
        | };
        |};
      """.stripMargin)
  }
}