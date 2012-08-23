package org.hyperscala.web.live

import org.hyperscala.{Unique, Container, PropertyAttribute}
import org.hyperscala.html.{Text, Title, HTMLTag}
import org.hyperscala.css.StyleSheetAttribute
import org.hyperscala.web.HTMLPage
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.event.TagCreated
import actors.threadpool.AtomicInteger

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LivePage extends HTMLPage {
  private val increment = new AtomicInteger(0)

  head.id := "liveHead"
  head.contents(0).id := "liveTitle"
  body.id := "liveBody"

  listeners.synchronous.filter(evt => true) {
    case evt: TagCreated if (evt.tag.isInstanceOf[Text]) => // Ignore Text elements being created
    case evt: PropertyChangeEvent if (evt.property == title) => // Ignore title property
    case evt: PropertyChangeEvent if (evt.property.isInstanceOf[PropertyAttribute[_]] && evt.property.parent.isInstanceOf[HTMLTag]) => {
      val tag = evt.property.parent.asInstanceOf[HTMLTag]
      if (tag.id() == null && !tag.isInstanceOf[Text]) {
        tag.id := Unique()
      }
      val id = increment.addAndGet(1)
      val attribute = evt.property.asInstanceOf[PropertyAttribute[_]]
      val change = LiveChange.attribute(id, tag, attribute)
//      println("Property: %s changed from %s to %s".format(evt.property.name(), evt.oldValue, evt.newValue))
      println(change)
    }
    case evt => println("*** Unhandled LivePage Event: %s".format(evt))
  }
}

/**
 * LiveConnections are created for each window representation of the LivePage. This allows LiveChange lists to be
 * trimmed and only include relevant changes for the specific window instance. Reloading a page will reset the
 * LiveConnection.
 */
class LiveConnection(created: Long = System.currentTimeMillis(), var lastUpdated: Long = System.currentTimeMillis()) {
  private var changes = Vector.empty[LiveChange]

  /**
   * Retrieves all changes since lastId or Nil if no changes.
   */
  def apply(lastId: Int): Seq[LiveChange] = synchronized {
    if (changes.nonEmpty) {
      val head = changes.head
      if (head.id <= lastId) {    // Client verified receipt of this id - remove and continue
        changes = changes.tail
        apply(lastId)
      } else {                    // Client hasn't receive this id, so send the rest of the list
        lastUpdated = System.currentTimeMillis()
        changes
      }
    } else {                      // No changes to send
      lastUpdated = System.currentTimeMillis()
      Nil
    }
  }

  def +=(change: LiveChange) = synchronized {
    if (change.key != null) {
      changes = changes.filterNot(c => c.key == change.key)   // Remove duplicates
    }
    changes = changes :+ change                               // Append the change
  }
}

case class LiveChange(id: Int, key: String, script: String)

object LiveChange {
  def attribute(id: Int, tag: HTMLTag, attribute: PropertyAttribute[_]) = {
    val key = "%s.%s".format(tag.id(), attribute.name())
    val script = if (tag.isInstanceOf[Title] && attribute.name() == "content") {
      "document.title = '%s';".format(attribute.attributeValue)
    } else {
      "liveLookup('%s').attr('%s', %s);".format(tag.id(), attribute.name(), attribute.attributeValue)
    }
    LiveChange(id, key, script)
  }

  def css(id: Int, tag: HTMLTag, attribute: StyleSheetAttribute[_]) = {
    val key = "%s.style.%s".format(tag.id(), attribute.name())
    val script = "liveLookup('%s').css('%s', %s);".format(tag.id(), attribute.name(), attribute.attributeValue)
    LiveChange(id, key, script)
  }

  def create(id: Int, tag: HTMLTag) = {
    val key = tag.id()
    val script = "document.livePage.%1$s = $(document.createElement('%2$s')).attr('id', '%1$s');".format(tag.id(), tag.xmlLabel)
    LiveChange(id, key, script)
  }

  def organize(id: Int, container: HTMLTag with Container[HTMLTag]) = {
    val key = "%s.children".format(container.id())
    val order = container.contents.map(c => "'%s'".format(c.id())).mkString("[", ", ", "]")
    val script = "liveOrganize('%s', %s);".format(container.id(), order)
    LiveChange(id, key, script)
  }
}