package org.hyperscala.examples.contenteditor

import com.outr.net.http.session.MapSession
import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.bootstrap.component.Button
import org.hyperscala.contenteditor.ContentEditor
import org.hyperscala.css.Style
import org.hyperscala.css.attributes.{Alignment, FontStyle, FontWeight}
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.javascript.dsl._
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ContentEditorExample(site: Website) extends Example {
  require(Realtime)
  require(Bootstrap)
  require(Gritter)

  val boldButton = styleToggleButton("Bold", Style.fontWeight, FontWeight.Bold, FontWeight.Inherit)
  val italicButton = styleToggleButton("Italic", Style.fontStyle, FontStyle.Italic, FontStyle.Inherit)
  val redButton = styleSetButton("Red", Style.color, Color.Red)
  val greenButton = styleSetButton("Green", Style.color, Color.Green)
  val blueButton = styleSetButton("Blue", Style.color, Color.Blue)
  val colorInput = new tag.Input(id = "currentColor", placeHolder = "Color")
  val fontFamily = new tag.Input(id = "fontFamily", placeHolder = "Font Family")
  val fontStyle = new tag.Input(id = "fontStyle", placeHolder = "Font Style")
  val fontSize = new tag.Input(id = "fontSize", placeHolder = "Font Size")
  val lineHeight = new tag.Input(id = "lineHeight", placeHolder = "Line Height")

  val frame = new RealtimeFrame("/example/wrapper/content_editor_content.html") {
    style.width := 100.pct
    style.height := 300.px
    style.border := "none"
    style.outline := "none"
  }
  val editorPage = new EditablePageExample(this)
  frame.currentPage := editorPage
  contents += frame

  def styleToggleButton[T, S <: Style[T]](label: String, style: S, value: T, reverse: T) = {
    val b = new Button(label = label)
    b.clickEvent.onRealtime {
      case evt => editorPage.editor.toggle(style, value, reverse)
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
          editorPage.editor.insert(new tag.Img(src = "http://www.freesmileys.org/emoticons/emoticon-animal-038.gif"))
        }
      }
    }
    contents += new Button(label = "Insert a Custom HTML Block") {
      mouseDownEvent.onRealtime {
        case evt => {
          editorPage.editor.insert(new tag.P {
            contents += "Buttons: "
            contents += new tag.Button(content = "Button 1")
            contents += new tag.Button(content = "Button 2")
            contents += new tag.Button(content = "Button 3")
          })
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
        case evt => editorPage.editor.adjustStyleSize(Style.fontSize, -2, 6, 120)
      }
    }
    contents += new Button(label = "Increase Size") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.adjustStyleSize(Style.fontSize, 2, 6, 120)
      }
    }
    contents += new Button(label = "Ordered List") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.wrapHTML(new tag.Ol(content = new tag.Li)) //editorPage.editor.orderedList()
      }
    }
    contents += new Button(label = "Unordered List") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.unorderedList()
      }
    }
    contents += new Button(label = "Clear Formatting") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.clearFormatting()
      }
    }
    contents += colorInput
    contents += fontFamily
    contents += fontStyle
    contents += fontSize
    contents += lineHeight
    contents += new Button(label = "Align Left") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.align(Alignment.Left)
      }
    }
    contents += new Button(label = "Align Center") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.align(Alignment.Center)
      }
    }
    contents += new Button(label = "Align Right") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.align(Alignment.Right)
      }
    }
    contents += new Button(label = "Align Justify") {
      mouseDownEvent.onRealtime {
        case evt => editorPage.editor.align(Alignment.Justify)
      }
    }
  }
  contents += controls
}

class EditablePageExample(example: ContentEditorExample) extends Webpage {
  require(ContentEditor)
  require(Gritter)

  val editableStyle = head.selector( """[contenteditable="true"]:active, [contenteditable="true"]:focus""")
  editableStyle.border := "none"
  editableStyle.outline := "none"
  val div = new tag.Div(id = "editor") {
    contentEditable := ContentEditable.True
    contents += new tag.P(content = "This is an example of the <span class=\"stylized-color\" style=\"color: red;\">ContentEditor</span> module functionality to assist editing content in-page.")
    contents += new tag.P(content = "Select a portion of this text and try the controls on the bottom of the page.")
  }
  val editor = ContentEditor(div)
  editor.autoApply := true
  editor.change.on {
    case evt => message("Contents Changed", div.outputText)
  }

  body.contents += div

  if (example != null) {
    editor.bindInput(example.colorInput, Style.color, parent)
    editor.bindStyle(example.colorInput, Style.color, Style.backgroundColor, Color.White, parent)
    editor.bindInput(example.fontFamily, Style.fontFamily, parent)
    editor.bindFontStyle(example.fontStyle, parent)
    editor.bindInput(example.fontSize, Style.fontSize, parent, ContentEditor.PixelConversion)
    editor.bindInput(example.lineHeight, Style.lineHeight, parent, valueCleaner = ContentEditor.PixelConversion, "div")
  }

  def message(title: String, message: String) = if (example != null) {
    Gritter.add(example.webpage, title, message, time = 5000)
  } else {
    Gritter.add(this, title, message, time = 5000)
  }
}