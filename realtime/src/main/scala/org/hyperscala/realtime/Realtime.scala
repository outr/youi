package org.hyperscala.realtime

import org.hyperscala.web.{WebpageHandler, Webpage, Website}

import org.hyperscala.html._
import annotation.tailrec

import org.powerscala.json._
import org.hyperscala.web.module.IdentifyTags
import org.powerscala.Version
import org.hyperscala.module._
import org.hyperscala.jquery.jQuery

import language.reflectiveCalls
import org.hyperscala.jquery.stylesheet.jQueryStyleSheet
import com.outr.net.communicator.server.{Message, Connection, Communicator}
import org.powerscala.log.Logging
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.html.attributes.InputType

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Realtime extends Module with Logging {
  def name = "realtime"

  def version = Version(1, 1)

  override def dependencies = List(jQuery.LatestWithDefault, jQueryStyleSheet, IdentifyTags)

  private val connectionsKey = "webpageConnections"

  def init() = {
    // Configure communicator resources
    Communicator.configure(Website())
    // Register realtime.js to actually establish the connection
    Website().register("/js/realtime.js", "realtime.js")
    // Listen for connections
    Communicator.created.on {
      case (connection, data) => {
        val pageId = data.asInstanceOf[Map[String, String]]("pageId")
        created(connection, pageId)
      }
    }
    Communicator.received.on {
      case (connection, message) => received(connection, message)
    }
    Communicator.disposed.on {
      case connection => disposed(connection)
    }
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(src = "/GWTCommunicator/GWTCommunicator.nocache.js")
    page.head.contents += new tag.Script(src = "/communicator.js")
    page.head.contents += new tag.Link(href = "/communicator.css")
    page.head.contents += new tag.Script(src = "/js/realtime.js")
    page.head.contents += new tag.Script(content = JavaScriptString(s"connectRealtime('${page.pageId}');"))
  }

  private def created(connection: Connection, pageId: String) = {
    val page = WebpageHandler.pageById[Webpage](pageId)
    val realtime = RealtimePage(page)             // Get reference to RealtimePage
    realtime.connectionCreated(connection)        // Notify the RealtimePage that a connection was created
    connection.store("page") = page               // Assign the page to the connection
  }

  private def received(connection: Connection, message: Message) = {
    val page = connection.store[Webpage]("page")
    val realtime = RealtimePage(page)
    realtime.received(connection, message)
  }

  private def disposed(connection: Connection) = {
    val page = connection.store[Webpage]("page")    // Load the page from the connection
    val realtime = RealtimePage(page)               // Get a reference to RealtimePage
    realtime.connectionDisposed(connection)         // Notify the RealtimePage that a connection was disposed
  }

  def broadcast(event: String, message: Any, page: Webpage = Webpage()) = synchronized {
    val realtime = RealtimePage(page)
    val connections = realtime.connections
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
  private def sendRecursive(page: Webpage, event: String, message: String, connections: List[Connection]): Unit = {
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
        field.changeEvent := RealtimeEvent()
        field match {
          case i: tag.Input => field.clickEvent := RealtimeEvent()
          case _ => // Not an input
        }
      }
    }
    Webpage().live[tag.Button] {
      case b => b.clickEvent := RealtimeEvent()
    }
  }

  /**
   * All change and click events fire events to the server and form submits prevent default and send event to server.
   */
  def connectForm() = {
    Webpage().live[FormField] {
      case field => {
        if (field.changeEvent() == null) {
          field.changeEvent := RealtimeEvent(preventDefault = false)
        }
        field match {
          case i: tag.Input if i.inputType() == InputType.Button => {
            if (field.clickEvent() == null) {
              field.clickEvent := RealtimeEvent(preventDefault = false)
            }
          }
          case _ => // Not a button input
        }
      }
    }
    Webpage().live[tag.Button] {
      case b => if (b.clickEvent() == null) b.clickEvent := RealtimeEvent(preventDefault = false)
    }
    Webpage().live[tag.Form] {
      case f => {
        if (f.submitEvent() == null) f.submitEvent := RealtimeEvent()
      }
    }
  }

  /**
   * Sends all form data over realtime upon form submit.
   */
  def connectPost() = {
    Webpage().live[tag.Form] {
      case f => if (f.submitEvent() == null) f.submitEvent := RealtimeEvent(fireChange = true)
    }
  }
}

case class JavaScriptMessage(instruction: String, content: String = null)