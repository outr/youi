package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.hyperscala.Unique
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.web.site.Webpage
import org.hyperscala.event.ChangeEvent
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.web.site.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RichEditor extends tag.Div {
  Webpage().require(CKEditor)

  val textArea = new tag.TextArea(id = Unique(), style = style()) {
    content.listeners.synchronous {
      case evt: PropertyChangeEvent => {
        // TODO: avoid sending back what we receive from the client
        // TODO: this would be much easier if textarea was still hearing changes in browser!!!
        Realtime.sendJavaScript("updateRichEditor('%s', content);".format(id()), content())
      }
    }
  }
  val content = textArea.content
  textArea.listeners.synchronous {    // Refire change events to the RichEditor
    case evt: ChangeEvent => RichEditor.this.fire(new ChangeEvent(evt.tag))
  }

  contents += textArea
  contents += new tag.Script {
    contents += JavaScriptString("createRichEditor('%s');".format(textArea.id()))
  }
}
