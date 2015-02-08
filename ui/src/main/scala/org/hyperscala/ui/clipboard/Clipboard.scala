package org.hyperscala.ui.clipboard

import com.outr.net.http.session.Session
import org.hyperscala.css.attributes.Display
import org.hyperscala.event.BrowserEvent
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.ui.Rangy
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version
import org.powerscala.enum.{EnumEntry, Enumerated}
import org.powerscala.event.Listenable
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.json.TypedSupport

/**
 * Clipboard offers a mechanism to manage storage and retrieval of items on the server level as an alternative for a
 * native clipboard.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Clipboard extends Module {
  TypedSupport.register("clipboard.cut", classOf[ClipboardCutEvent])
  TypedSupport.register("clipboard.copy", classOf[ClipboardCopyEvent])
  TypedSupport.register("clipboard.paste", classOf[ClipboardPasteEvent])

  /**
   * Creates a new ClipboardInstance each time it is called (once per webpage instance).
   */
  val WebpageInstanceCreator = (webpage: Webpage[_ <: Session]) => new ClipboardInstance(webpage)
  /**
   * Stores the ClipboardInstance in the session.
   */
  val SessionInstanceCreator = (webpage: Webpage[_ <: Session]) => webpage.website.session.getOrSet("clipboard_module", new ClipboardInstance(webpage))

  val name = "clipboard"
  val version = Version(1)

  override def dependencies = List(jQuery, Realtime, Rangy)

  /**
   * Creates new ClipboardInstances. This can be overridden to pre-populate or share instances across multiple pages.
   *
   * By default a single instance is tied to a single webpage instance (WebpageInstanceCreator).
   */
  var creator: (Webpage[_ <: Session]) => ClipboardInstance = WebpageInstanceCreator

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/clipboard.js", "clipboard.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/clipboard.js")
    apply(webpage)   // Make sure the clipboard instance is created
  }

  def apply[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet("clipboard_module", creator(webpage))

  def connect(tags: HTMLTag*) = tags.foreach {
    case t => if (!t.clazz.contains("use-clipboard")) {
      t.clazz += "use-clipboard"
    }
  }
}

class ClipboardInstance(webpage: Webpage[_ <: Session]) extends Listenable {
  val cutEvent = new UnitProcessor[ClipboardCutEvent]("cut")
  val copyEvent = new UnitProcessor[ClipboardCopyEvent]("copy")
  val pasteEvent = new UnitProcessor[ClipboardPasteEvent]("paste")

  /**
   * Fired when a ClipboardEntry is added to the clipboard. This may occur programmatically or resulting from handling
   * on a <code>clientEvent</code>.
   */
  val entryAdded = new UnitProcessor[ClipboardEntry]("clipboard_entry")

  /**
   * Configures basic handling of clientEvents for Cut, Copy, and Paste.
   */
  def configureDefaultHandling() = {
    cutEvent.on {
      case evt => if (evt.selected != null && evt.selected.nonEmpty) {
        this += BasicClipboardEntry("html", "HTML Content", evt.selected, Option(evt.tag))
      }
    }
    copyEvent.on {
      case evt => if (evt.selected != null && evt.selected.nonEmpty) {
        this += BasicClipboardEntry("html", "HTML Content", evt.selected, Option(evt.tag))
      }
    }
  }

  private var _list = List.empty[ClipboardEntry]

  webpage.json.on {
    case evt: ClipboardCutEvent => cutEvent.fire(evt)
    case evt: ClipboardCopyEvent => copyEvent.fire(evt)
    case evt: ClipboardPasteEvent => pasteEvent.fire(evt)
    case _ => // Ignore others
  }

  def +=(entry: ClipboardEntry): Unit = synchronized {
    _list = entry :: _list
    entryAdded.fire(entry)
  }

  def headOption = list.headOption

  def headByType(entryType: String) = list.find(entry => entry.entryType == entryType)

  /**
   * Removes all entries from clipboard.
   */
  def clear() = synchronized {
    _list = List.empty
  }

  def list = _list
}

case class ClipboardCutEvent(tag: HTMLTag, mouseX: Int, mouseY: Int, selected: String)
case class ClipboardCopyEvent(tag: HTMLTag, mouseX: Int, mouseY: Int, selected: String)
case class ClipboardPasteEvent(tag: HTMLTag, mouseX: Int, mouseY: Int, selected: String)

trait ClipboardEntry {
  def entryType: String
  def description: String
  def value: Any
  def owner: Option[HTMLTag]
  def timestamp: Long
}

case class BasicClipboardEntry(entryType: String,
                               description: String,
                               value: Any,
                               owner: Option[HTMLTag],
                               timestamp: Long = System.currentTimeMillis()) extends ClipboardEntry