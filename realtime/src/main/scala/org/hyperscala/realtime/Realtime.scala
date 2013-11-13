package org.hyperscala.realtime

import org.hyperscala.web.{WebpageHandler, Webpage, Website}

import org.hyperscala.html._

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
import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.selector.Selector
import org.powerscala.property.Property
import org.hyperscala.{Markup, Container}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Realtime extends Module with Logging {
  val debug = Property[Boolean]()

  def name = "realtime"

  def version = Version(1, 1)

  override def dependencies = List(jQuery.LatestWithDefault, jQueryStyleSheet, IdentifyTags)

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
    Communicator.connected.on {
      case (connection, data) => {
        connected(connection)
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
    page.head.contents += new tag.Script(content = JavaScriptString(s"jQuery(document).ready(function() { connectRealtime('${page.pageId}', ${debug()}); });"))
  }

  private def created(connection: Connection, pageId: String) = {
    WebpageHandler.pageById[Webpage](pageId) match {
      case Some(page) => {
        val realtime = RealtimePage(page)             // Get reference to RealtimePage
        realtime.connectionCreated(connection)        // Notify the RealtimePage that a connection was created
        connection.store("page") = page               // Assign the page to the connection
      }
      case None => warn(s"Unable to find page by id: $pageId (cached page ids: ${Website().pages.ids.mkString(", ")})")
    }
  }

  private def connected(connection: Connection) = {
    val page = connection.store[Webpage]("page")
    val realtime = RealtimePage(page)
    realtime.connectionConnected(connection)
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

  def broadcast(event: String, message: Any, sendWhenConnected: Boolean, page: Webpage = Webpage()) = {
    Webpage().require(this)
    val realtime = RealtimePage(page)
    val content = message match {
      case s: String => s
      case other => generate(other, specifyClassName = false)
    }
    realtime.send(event, content, sendWhenConnected = sendWhenConnected)
  }

  /**
   * Declare a created element via 'creator' as "existing". This returns the created element connected to the parent
   * <i>without</i> sending any realtime communication back to the browser to actually create or add it. This presumes
   * that on the client the element already exists so should not be created. This can be extremely useful when other
   * systems or libraries are creating or modifying content on the client independent of your server-side DOM.
   *
   * @param parent the parent the existing element is already attached to
   * @param creator the function to create the element
   * @tparam T the type of the HTMLTag that already exists
   * @return existing T
   */
  def existing[T <: HTMLTag](parent: Container[_], creator: => T) = RealtimePage.ignoreStructureChanges {
    val element: T = creator
    if (element.id() == null || element.id() == "") {
      throw new RuntimeException("The created element must have an existing ID supplied for referencing!")
    }
    parent.asInstanceOf[Container[T]].contents += element
    Markup.rendered(element)      // Mark the element as rendered so it doesn't wait for it
    element
  }

  /**
   * Sends JavaScript to the client.
   *
   * @param instruction the instruction to evaluate on the client
   * @param content optionally contains raw content and can be referenced by name in instruction
   * @param selector optionally specifies a selector that must be non-empty before the instruction will be invoked
   * @param onlyRealtime if true the JavaScript will only be sent if the page has already completed rendering (default: true)
   * @param delay optionally specifies a delay before the instruction is invoked
   */
  def sendJavaScript(instruction: String, content: String = null, selector: Selector = null, onlyRealtime: Boolean = true, delay: Int = 0): Unit = {
    broadcast("eval", JavaScriptMessage(instruction, content, selector, delay), sendWhenConnected = !onlyRealtime)
  }

  def sendRedirect(url: String) = {
    sendJavaScript("window.location.href = content;", url, onlyRealtime = false)
  }

  def send(statement: Statement, selector: Selector = null, onlyRealtime: Boolean = false) = {
    Realtime.sendJavaScript(statement.content, selector = selector, onlyRealtime = onlyRealtime)
  }

  def reload(fresh: Boolean = false) = {
    sendJavaScript("location.reload(%s);".format(fresh))
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

case class JavaScriptMessage(instruction: String, content: String = null, selector: Selector = null, delay: Int = 0)