package org.hyperscala.realtime

import org.hyperscala.web.Webpage
import org.hyperscala.html.tag
import org.hyperscala.javascript.JavaScriptString
import com.outr.net.communicator.server.{Message, Connection}
import org.powerscala.hierarchy.event.{ChildAddedEvent, ChildRemovedEvent, Descendants}
import org.powerscala.Priority
import org.powerscala.property.event.PropertyChangeEvent
import org.powerscala.log.Logging
import org.hyperscala.css.StyleSheet
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimePage private(page: Webpage) extends Logging {
  private var _connections = List.empty[Connection]
  def connections = _connections

  // Configure JavaScript on page
  page.head.contents += new tag.Script(src = "/GWTCommunicator/GWTCommunicator.nocache.js")
  page.head.contents += new tag.Script(src = "/communicator.js")
  page.head.contents += new tag.Link(href = "/communicator.css")
  page.head.contents += new tag.Script(src = "/js/realtime.js")
  page.head.contents += new tag.Script(content = JavaScriptString(s"connectRealtime('${page.pageId}');"))

  // Configure Listeners
  page.childAdded.listen(Priority.Normal, Descendants) {
    case evt => childAdded(evt)
  }
  page.childRemoved.listen(Priority.Normal, Descendants) {
    case evt => childRemoved(evt)
  }
  page.head.styleSpaces.removed.on {
    case evt => styleSheetRemoved(evt.value)
  }
  page.listen[PropertyChangeEvent[_], Unit, Unit]("change", Priority.Normal, Descendants) {
    case evt => propertyChanged(evt)
  }

  // TODO: handle checkins to keep page alive
  // TODO: utilize ignore values in companion

  protected[realtime] def connectionCreated(connection: Connection) = synchronized {
    _connections = connection :: _connections
  }

  protected[realtime] def received(connection: Connection, message: Message) = synchronized {
    val event = message.event
    info(s"Received: $message")
  }

  protected[realtime] def connectionDisposed(connection: Connection) = synchronized {
    _connections = _connections.filterNot(c => c == connection)
  }

  private def childAdded(evt: ChildAddedEvent) = {
    info(s"childAdded: $evt")
  }

  private def childRemoved(evt: ChildRemovedEvent) = {
    info(s"childRemoved: $evt")
  }

  private def styleSheetRemoved(styleSheet: StyleSheet) = {
    info(s"styleSheetRemoved: $styleSheet")
  }

  private def propertyChanged(evt: PropertyChangeEvent[_]) = {
    info(s"propertyChanged: $evt")
  }
}

object RealtimePage {
  private val _ignoringChangeProperty = new ThreadLocal[Property[_]]
  private val _ignoringChangeValue = new ThreadLocal[Any]
  private val _ignoringStructureChanges = new ThreadLocal[Boolean] {
    override def initialValue() = false
  }
  def ignoreStructureChanges[T](f: => T): T = {     // TODO: verify this will work with multiple connections to the same page
  val set = _ignoringStructureChanges.get()
    _ignoringStructureChanges.set(true)
    try {
      f
    } finally {
      if (!set) {
        _ignoringStructureChanges.set(false)
      }
    }
  }
  def ignoringStructureChanges = _ignoringStructureChanges.get()
  def ignoringChange[T](property: Property[T], value: T) = {
    _ignoringChangeProperty.set(property)
    _ignoringChangeValue.set(value)
    try {
      property := value
    } finally {
      _ignoringChangeProperty.remove()
      _ignoringChangeValue.remove()
    }
  }

  def apply(page: Webpage) = page.synchronized {
    page.store.getOrSet("realtime", new RealtimePage(page))
  }
}