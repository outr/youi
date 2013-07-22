package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.hyperscala.Unique
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.web.site.Webpage
import org.hyperscala.event.ChangeEvent
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RichEditor extends tag.Div {
  Webpage().require(CKEditor)

  val textArea = new tag.TextArea(id = Unique(), style = style) {
    content.change.on {
      case evt => {
        // TODO: avoid sending back what we receive from the client
        // TODO: this would be much easier if textarea was still hearing changes in browser!!!
        Realtime.sendJavaScript("updateRichEditor('%s', content);".format(id()), content())
      }
    }
  }
  val content = textArea.content
  textArea.changeEvent.on {    // Refire change events to the RichEditor
    case evt => RichEditor.this.fire(new ChangeEvent(evt.tag))
  }

  contents += textArea
  contents += new tag.Script {
    contents += JavaScriptString("createRichEditor('%s');".format(textArea.id()))
  }
}
