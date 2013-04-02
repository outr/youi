package org.hyperscala.realtime

import org.hyperscala.web.site.{WebpageConnection, Webpage, Website}

import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptContent
import java.util.UUID
import annotation.tailrec

import org.powerscala.json._
import org.hyperscala.web.module.IdentifyTags
import org.hyperscala.web.site.JavaScriptMessage
import org.powerscala.Version
import org.hyperscala.module._
import org.hyperscala.jquery.jQuery
import org.hyperscala.event.JavaScriptEvent

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Realtime extends Module {
  var debug = false

  def name = "realtime"

  def version = Version(1)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest), IdentifyTags)

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
        "connectRealtime('%s', %s);".format(id.toString, debug)
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

  def addConnection(page: Webpage, connection: WebpageConnection) = synchronized {
    val connections = page.store.getOrElse[List[WebpageConnection]](connectionsKey, Nil).filterNot(c => c.disposed)
    page.store(connectionsKey) = connection :: connections
  }

  def broadcast(event: String, message: Any, page: Webpage = Webpage()) = synchronized {
    val connections = getConnections(page)
    val content = message match {
      case s: String => s
      case other => generate(other)
    }
    sendRecursive(page, event, content, connections)
  }

  def sendJavaScript(instruction: String, content: String = null, forId: String = null, head: Boolean = true, onlyRealtime: Boolean = true) = {
    if (Webpage().rendered) {
      Webpage().require(this)

      if (forId != null) {
        val s = """
                  |invokeForId('%s', function() {
                  | %s
                  |});
                """.stripMargin.format(forId, instruction)
        if (content != null) {
          throw new RuntimeException("forId not supported with non-null content")
        }
        broadcast("eval", JavaScriptMessage(s, content))
      } else {
        broadcast("eval", JavaScriptMessage(instruction, content))
      }
    } else if (!onlyRealtime) {
      val script = instruction.replaceAll("content", content)
      val s = new tag.Script {
        contents += new JavaScriptContent {
          def content = script
        }
      }
      if (head) {
        Webpage().head.contents += s
      } else {
        Webpage().body.contents += s
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
        field.event.change := JavaScriptEvent()
        field match {
          case i: tag.Input => field.event.click := JavaScriptEvent()
          case _ => // Not an input
        }
      }
    }
    Webpage().live[tag.Button] {
      case b => b.event.click := JavaScriptEvent()
    }
  }

  /**
   * All change and click events fire events to the server and form submits prevent default and send event to server.
   */
  def connectForm() = {
    Webpage().live[FormField] {
      case field => {
        field.event.change := JavaScriptEvent(preventDefault = false)
        field match {
          case i: tag.Input => if (field.event.click() == null) {
            field.event.click := JavaScriptEvent(preventDefault = false)
          }
          case _ => // Not an input
        }
      }
    }
    Webpage().live[tag.Button] {
      case b => if (b.event.click() == null) b.event.click := JavaScriptEvent(preventDefault = false)
    }
    Webpage().live[tag.Form] {
      case f => {
        if (f.event.submit() == null) f.event.submit := JavaScriptEvent()
      }
    }
  }

  /**
   * Sends all form data over realtime upon form submit.
   */
  def connectPost() = {
    Webpage().live[tag.Form] {
      case f => if (f.event.submit() == null) f.event.submit := JavaScriptEvent(fireChange = true)
    }
  }
}
