package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.hyperscala.web.site.{Website, Webpage}
import org.hyperscala.realtime.Realtime
import org.hyperscala.module.Module
import org.powerscala.{Version, StorageComponent}
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <matt@outr.com>
 */
object RichEditor extends Module with StorageComponent[RichEditor, HTMLTag] {
  def name = "RichEditor"
  def version = Version(2)

  override def dependencies = List(jQuery.LatestWithDefault, Realtime, CKEditor)

  def init() = {
    Website().register("/js/rich_editor.js", "rich_editor.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/rich_editor.js")
  }

  override def apply(t: HTMLTag) = {
    Webpage().require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new RichEditor(t)
}

class RichEditor private(t: HTMLTag) {

}

/*class RichEditor extends tag.Div with FormField {
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
}*/
