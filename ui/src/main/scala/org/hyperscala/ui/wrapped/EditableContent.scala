package org.hyperscala.ui.wrapped

import org.powerscala.{Version, StorageComponent}
import org.hyperscala.html.{tag, HTMLTag}
import org.hyperscala.web.site.{WebpageConnection, Website, Webpage}
import org.hyperscala.realtime.Realtime
import org.hyperscala.html.attributes.ContentEditable
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.jquery.jQuery
import org.powerscala.event.Intercept
import org.hyperscala.io.HTMLToScala
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.Container
import org.jdom2.Element
import org.powerscala.event.processor.UnitProcessor

/**
 * @author Matt Hicks <matt@outr.com>
 */
object EditableContent extends Module with StorageComponent[EditableContent, HTMLTag with Container[BodyChild]] {
  def name = "EditableContent"
  def version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault, Realtime)

  def init() {
    Website().register("/js/editable_content.js", "editable_content.js")
  }

  def load() {
    Webpage().head.contents += new tag.Script(src = "/js/editable_content.js")
  }

  override def apply(tag: HTMLTag with Container[BodyChild]) = {
    Webpage().require(this)
    super.apply(tag)
  }

  protected def create(t: HTMLTag with Container[BodyChild]) = new EditableContent(t)
}

class EditableContent(t: HTMLTag with Container[BodyChild]) {
  val contentChanged = new UnitProcessor[ContentChanged]("contentChanged")(t, implicitly[Manifest[ContentChanged]])

  t.contentEditable := ContentEditable.True

  Realtime.sendJavaScript(s"initEditableContent('${t.identity}');", forId = t.identity, onlyRealtime = false)
  t.eventReceived.on {
    case evt if evt.event == "htmlChanged" => {
      val htmlString = evt.message[String]("html")
      val xml = HTMLToScala.toXML(htmlString, clean = true)
      val body = xml.getChild("body")
      WebpageConnection.ignoreStructureChanges {      // Keep WebpageConnection from sending the changes to the client
        t.contents.clear()                            // Remove all contents
        t.read(body)                                  // Read the new data back in
      }
      contentChanged.fire(ContentChanged(xml, htmlString))
      Intercept.Stop
    }
    case _ => Intercept.Continue
  }

  def bold() = execCommand("bold")

  def execCommand(command: String, value: Any = null) = {
    Realtime.sendJavaScript(s"editableContentExecCommand('${t.identity}', '$command', content);", JavaScriptContent.toJS(value))
  }
}

case class ContentChanged(xml: Element, htmlString: String)