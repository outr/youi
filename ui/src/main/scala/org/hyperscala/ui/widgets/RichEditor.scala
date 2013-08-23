package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.hyperscala.Message
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime
import org.powerscala.property.Property
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RichEditor extends tag.Div with FormField {
  Webpage().require(CKEditor)

  private val _changing = new AtomicBoolean(false)

  val disabled = Property[Boolean](default = Some(false))
  val value = Property[String](default = Some(""))

  protected def validateFrequency = 1000

  private def editorId = identity

  value.change.on {
    case evt => if (!_changing.get()) {
      Realtime.sendJavaScript(s"updateRichEditor('$editorId', content);", evt.newValue, onlyRealtime = false)
    }
  }

  contents += new tag.Script {
    contents += JavaScriptString(s"createRichEditor('$editorId', $validateFrequency);")
  }

  onInit {
    if (value() == "") {
      changeValue(contents.tail.map(c => c.outputString).mkString(" "))
    }
  }

  override def receive(event: String, message: Message) = event match {
    case "editorChanged" => {
      val content = message[String]("value")
      changeValue(content)
    }
    case _ => super.receive(event, message)
  }

  /**
   * Changes 'value' without triggering an event back to the client.
   *
   * @param content to set value to
   */
  private def changeValue(content: String) = {
    _changing.set(true)
    try {
      value := content
    } finally {
      _changing.set(false)
    }
  }
}
