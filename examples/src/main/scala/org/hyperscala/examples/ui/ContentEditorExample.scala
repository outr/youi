package org.hyperscala.examples.ui

import com.outr.net.http.session.Session
import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.css.attributes.{FontStyle, Display}
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.web._
import org.hyperscala.jquery.dsl._
import org.hyperscala.examples.Example
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.ui.Rangy
import org.hyperscala.realtime._
import org.hyperscala.realtime.dsl._
import org.powerscala.{Version, Color}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ContentEditorExample extends Example {
  require(ContentEditor)

//  connected[tag.HTML] {
//    case html => {
//      val js =
//        """
//          |window.onload = function() {
//          | rangy.init();
//          |});
//        """.stripMargin
//      html.head.contents += new tag.Script(content = JavaScriptString(js))
//    }
//  }

  val div = new tag.Div(id = "editor") {
    inputEvent.onRealtime {
      case evt => println("InputEvent!")
    }
    contents += new tag.P(content = "This is an example of the ContentEditor module functionality to assist editing content in-page.")
    contents += new tag.P(content = "Click the \"Start Editing\" button below to make this content editable.")
  }
  contents += div

  val startEditing = new tag.Button(content = "Start Editing") {
    clickEvent.onRealtime {
      case evt => edit()
    }
  }
  contents += startEditing

  val controls = new tag.Div {
    style.display := Display.None

    contents += new tag.Button(content = "Selection Stats") {
      clickEvent.onRealtime {
//        case evt => Realtime.sendJavaScript(this.webpage, """alert('Font-Size: ' + getSelectionStyle('fontSize') + '\nColor: ' + getSelectionStyle('color') + '\nBackground-Color: ' + getSelectionStyle('backgroundColor') + '\nFont-Style: ' + getSelectionStyle('fontStyle') + '\nFont-Weight: ' + getSelectionStyle('fontWeight'));""")
        case evt => Realtime.sendJavaScript(this.webpage, """alert('Font-Weight: ' + getSelectionStyle('fontWeight'));""")
      }
    }
    contents += new tag.Button(content = "Bold") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('fontWeight', 'bold', ['700']);")
      }
    }
    contents += new tag.Button(content = "Italics") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('fontStyle', 'italic');")
      }
    }
    contents += new tag.Button(content = "Red") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('fontColor', 'red');")
      }
    }
    contents += new tag.Button(content = "Green") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('fontColor', 'green');")
      }
    }
    contents += new tag.Button(content = "Blue") {
      mouseDownEvent.onRealtime {
        case evt => Realtime.sendJavaScript(this.webpage, "toggleStyle('fontColor', 'blue');")
      }
    }
//    contents += new tag.Button(content = "Test") {
//      mouseDownEvent.onRealtime {
//        case evt => Realtime.sendJavaScript(this.webpage, "test();")
//      }
//    }
  }
  contents += controls

  def edit(): Unit = {
    div.contentEditable := ContentEditable.True
    startEditing.style.display := Display.None
    controls.style.display := Display.Block
    $(div).focus().send(this.webpage)
  }
}

object ContentEditor extends Module {
  val name = "contentEditor"
  val version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault, Realtime, Rangy)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/undo.js", "undo.js")
    website.register("/js/medium.js", "medium.js")
    website.register("/js/content_editor.js", "content_editor.js")
  }

  override def load[S <: Session](page: Webpage[S]) = {
    page.head.contents += new tag.Script(src = "/js/undo.js")
    page.head.contents += new tag.Script(src = "/js/medium.js")
    page.head.contents += new tag.Script(src = "/js/content_editor.js")
  }
}