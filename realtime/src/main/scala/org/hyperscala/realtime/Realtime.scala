package org.hyperscala.realtime

import org.hyperscala.web.{Webpage, Website}

import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptContent
import java.util.UUID
import annotation.tailrec

import org.powerscala.json._
import org.hyperscala.web.module.IdentifyTags
import org.powerscala.Version
import org.hyperscala.module._
import org.hyperscala.jquery.jQuery
import org.hyperscala.event.JavaScriptEvent

import language.reflectiveCalls
import org.hyperscala.jquery.stylesheet.jQueryStyleSheet
import org.hyperscala.{Markup, Container}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Realtime extends Module {
  var debug = false

  def name = "realtime"

  def version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault, jQueryStyleSheet, IdentifyTags)

  def init() = {
    Website().register("/js/communicator.js", "communicator.js")
    Website().register("/js/realtime.js", "realtime.js")
  }

  def load() = {
    val page = Webpage()
    // Configure JavaScript on page
    page.head.contents += new tag.Script(src = "/js/communicator.js")
    page.head.contents += new tag.Script(src = "/js/realtime.js")
    page.head.contents += new tag.Script(content = new JavaScriptContent {
      def content = {   // Every page request we create a new connection
        val id = UUID.randomUUID()
        val connection = Website().create(id)
        connection.page = page
        Realtime.addConnection(page, connection)
        s"""$$(document).ready(function() {
          |   connectRealtime('${id.toString}', $debug);
          |});
        """.stripMargin
      }

      protected def content_=(content: String) {}
    })
  }

  private val connectionsKey = "webpageConnections"

  def getConnections(page: Webpage) = synchronized {
    val connections = page.store.getOrElse[List[WebpageConnection]](connectionsKey, Nil)
    val updated = connections.filterNot(c => c.disposed)
    if (updated != connections) {
      page.store(connectionsKey) = updated
    }
    connections
  }

  def existing[T <: HTMLTag](parent: Container[_], creator: => T) = WebpageConnection.ignoreStructureChanges {
    val element: T = creator
    if (element.id() == null || element.id() == "") {
      throw new RuntimeException("The created element must have an existing ID supplied for referencing!")
    }
    parent.asInstanceOf[Container[T]].contents += element
    Markup.rendered(element)      // Mark the element as rendered so it doesn't wait for it
    element
  }

  def addConnection(page: Webpage, connection: WebpageConnection) = synchronized {
    val connections = page.store.getOrElse[List[WebpageConnection]](connectionsKey, Nil).filterNot(c => c.disposed)
    page.store(connectionsKey) = connection :: connections
  }

  def broadcast(event: String, message: Any, page: Webpage = Webpage()) = synchronized {
    val connections = getConnections(page)
    if (connections.isEmpty) {
      throw new RuntimeException(s"Unable to send $event, no connections established!")
    }
    val content = message match {
      case s: String => s
      case other => generate(other)
    }
    sendRecursive(page, event, content, connections)
  }

  private def wrapInInvokeForId(id: String, instruction: String) = {
    """
      |invokeForId('%s', function() {
      | %s
      |});
    """.stripMargin.format(id, instruction)
  }

  private def wrapInDelay(instruction: String, delay: Int) = if (delay > 0) {
    """
      |setTimeout(function() {
      | %s
      |}, %s);
    """.stripMargin.format(instruction, delay)
  } else {
    instruction
  }

  def sendJavaScript(instruction: String, content: String = null, forId: String = null, head: Boolean = true, onlyRealtime: Boolean = true, delay: Int = 0): Unit = {
    val i = wrapInDelay(instruction, delay)
    Webpage().require(this)
    val sendFunction = new Function0[Unit] {
      def apply() = {
        if (forId != null) {
          if (content != null) {
            throw new RuntimeException("forId not supported with non-null content")
          }
          broadcast("eval", JavaScriptMessage(wrapInInvokeForId(forId, i), content))
        } else {
          broadcast("eval", JavaScriptMessage(i, content))
        }
      }
    }
    if (Webpage().rendered) {
      sendFunction()
    } else if (!onlyRealtime) {
      Webpage().body.onAfterRender {
        sendFunction()
      }
    }
  }

  def sendRedirect(url: String) = {
    sendJavaScript("window.location.href = content;", url)
  }

  def reload(fresh: Boolean = false) = {
    sendJavaScript("location.reload(%s);".format(fresh))
  }

  @tailrec
  private def sendRecursive(page: Webpage, event: String, message: String, connections: List[WebpageConnection]): Unit = {
    if (connections.nonEmpty) {
      val c = connections.head
      c.send(event, message)
      sendRecursive(page, event, message, connections.tail)
    }
  }

  /**
   * Connects change events for FormField (input, textarea, and select) as well as click events on button and input.
   */
  def connectStandard() = {
    Webpage().live[FormField] {
      case field => {
        field.changeEvent := JavaScriptEvent()
        field match {
          case i: tag.Input => field.clickEvent := JavaScriptEvent()
          case _ => // Not an input
        }
      }
    }
    Webpage().live[tag.Button] {
      case b => b.clickEvent := JavaScriptEvent()
    }
  }

  /**
   * All change and click events fire events to the server and form submits prevent default and send event to server.
   */
  def connectForm() = {
    Webpage().live[FormField] {
      case field => {
        if (field.changeEvent() == null) {
          field.changeEvent := JavaScriptEvent(preventDefault = false)
        }
        field match {
          case i: tag.Input => if (field.clickEvent() == null) {
            field.clickEvent := JavaScriptEvent(preventDefault = false)
          }
          case _ => // Not an input
        }
      }
    }
    Webpage().live[tag.Button] {
      case b => if (b.clickEvent() == null) b.clickEvent := JavaScriptEvent(preventDefault = false)
    }
    Webpage().live[tag.Form] {
      case f => {
        if (f.submitEvent() == null) f.submitEvent := JavaScriptEvent()
      }
    }
  }

  /**
   * Sends all form data over realtime upon form submit.
   */
  def connectPost() = {
    Webpage().live[tag.Form] {
      case f => if (f.submitEvent() == null) f.submitEvent := JavaScriptEvent(fireChange = true)
    }
  }
}
