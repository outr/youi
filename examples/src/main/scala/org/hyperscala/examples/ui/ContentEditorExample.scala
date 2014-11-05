package org.hyperscala.examples.ui

import com.outr.net.http.session.MapSession
import org.hyperscala.bootstrap.component.Button
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.realtime._
import org.hyperscala.ui.ContentEditor
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ContentEditorExample(site: Website[MapSession]) extends Example {
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
    contents += new Button(label = "Selection Stats") {
      clickEvent.onRealtime {
        case evt => Realtime.sendJavaScript(editorPage, """alert('Font-Weight: ' + selectionStyle('fontWeight') + ', Color: ' + selectionStyle('color'));""")
      }
    }
    contents += new Button(label = "Bold") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(editorPage, "toggleStyle('editor', 'fontWeight', ['bold', '700'], null);")
      }
    }
    contents += new Button(label = "Italics") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(editorPage, "toggleStyle('editor', 'fontStyle', ['italic'], null);")
      }
    }
    contents += new Button(label = "Red") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(editorPage, "toggleStyle('editor', 'color', ['red', 'rgb(255, 0, 0)'], null);")
      }
    }
    contents += new Button(label = "Green") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(editorPage, "toggleStyle('editor', 'color', ['green', 'rgb(0, 128, 0)'], null);")
      }
    }
    contents += new Button(label = "Blue") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(editorPage, "toggleStyle('editor', 'color', ['blue', 'rgb(0, 0, 255)'], null);")
      }
    }
    contents += new Button(label = "Insert Kitty") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(editorPage, "insertImage('editor', 'http://www.freesmileys.org/emoticons/emoticon-animal-038.gif');")
      }
    }
    contents += new Button(label = "Test") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(editorPage, "alert('Select in container? ' + selectionInContainer('editor'));")
      }
    }
    contents += new tag.Input(id = "currentColor", value = "black") {
      changeEvent.onRealtime {
        case evt => {
          println(s"current color changed to: ${value()}")
          Realtime.sendJavaScript(editorPage, s"setStyle('editor', 'color', ${JavaScriptContent.toJS(value())});")
        }
      }
    }
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

    inputEvent.onRealtime {
      case evt => println("InputEvent!")
    }
    contents += new tag.P(content = "This is an example of the <span class=\"stylized\" style=\"color: red;\">ContentEditor</span> module functionality to assist editing content in-page.")
    contents += new tag.P(content = "Click the \"Start Editing\" button below to make this content editable.")
  }
  body.contents += div

  body.contents += new tag.Script {
    contents += new JavaScriptString(
      """
        |document.addEventListener('DOMContentLoaded', function() {
        |  var currentColor = window.parent.document.getElementById('currentColor');
        |  console.log('Test: ' + currentColor);
        |
        |  addSelectionStyleChangeListener(document.getElementById('editor'), 'color', function(oldValue, newValue) {
        |   currentColor.value = newValue;
        |   console.log('Color changed from: ' + oldValue + ' to ' + newValue);
        |  });
        |});
      """.stripMargin)
  }
}