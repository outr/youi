package org.hyperscala.realtime

import org.hyperscala.web.{Webpage, Website}

import org.hyperscala.html._

import org.hyperscala.web.module.IdentifyTags
import org.powerscala.Version
import org.hyperscala.module._
import org.hyperscala.jquery.jQuery

import language.reflectiveCalls
import org.hyperscala.jquery.stylesheet.jQueryStyleSheet
import org.powerscala.log.Logging
import org.hyperscala.html.attributes.InputType
import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.selector.Selector
import org.powerscala.property.Property
import org.hyperscala.{Markup, Container}
import org.hyperscala.connect.{Message, Connection, Connect}
import org.powerscala.event.Listenable
import argonaut.{CodecJson, Json}
import argonaut.Argonaut._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Realtime extends Module with Logging with Listenable {
  val debug = Property[Boolean]()

  def name = "realtime"

  def version = Version(1, 1)

  override def dependencies = List(jQuery.LatestWithDefault, jQueryStyleSheet, IdentifyTags, Connect)

  override def init[S <: Session](website: Website[S]) = {
    // Register realtime.js to actually establish the connection
    website.register("/js/realtime.js", "realtime.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Meta(httpEquiv = "expires", content = "0")
    webpage.head.contents += new tag.Script(src = "/js/realtime.js")
    Connect.event[S](webpage) {
      case (connection, message) => received(connection, message)
    }(webpage.website.manifest)
  }

  private def received[S <: Session](connection: Connection[S], message: Message) = {
    val page = connection.webpage
    val realtime = RealtimePage(page)
    realtime.received(connection, message)
  }

  def broadcast[S <: Session](event: String, message: Json, sendWhenConnected: Boolean, page: Webpage[S]) = {
    page.require(this)
    val realtime = RealtimePage(page)
    realtime.send(event, message, sendWhenConnected = sendWhenConnected)
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
  // TODO: deprecate in favor of $('#busyDialog')['dialog')(json);
  def sendJavaScript[S <: Session](webpage: Webpage[S],
                                   instruction: String,
                                   content: Option[String] = None,
                                   selector: Option[Selector] = None,
                                   onlyRealtime: Boolean = true,
                                   delay: Int = 0): Unit = {
    broadcast("eval", JavaScriptMessage(instruction, content, selector.map(s => s.content), delay).asJson, sendWhenConnected = !onlyRealtime, page = webpage)
  }

  def sendRedirect[S <: Session](webpage: Webpage[S], url: String) = {
    sendJavaScript(webpage, "window.location.href = content;", Some(url), onlyRealtime = false)
  }

  def send[S <: Session](webpage: Webpage[S], statement: Statement[_], selector: Option[Selector] = None, onlyRealtime: Boolean = false) = {
    Realtime.sendJavaScript(webpage, statement.content, selector = selector, onlyRealtime = onlyRealtime)
  }

  def reload[S <: Session](webpage: Webpage[S], fresh: Boolean = false) = {
    sendJavaScript(webpage, "location.reload(%s);".format(fresh))
  }

  /**
   * Connects change events for FormField (input, textarea, and select) as well as click events on button and input.
   */
  def connectStandard[S <: Session](webpage: Webpage[S]) = {
    webpage.live[FormField] {
      case field => {
        field.changeEvent := RealtimeEvent()
        field match {
          case i: tag.Input => field.clickEvent := RealtimeEvent()
          case _ => // Not an input
        }
      }
    }
    webpage.live[tag.Button] {
      case b => b.clickEvent := RealtimeEvent()
    }
  }

  /**
   * All change and click events fire events to the server and form submits prevent default and send event to server.
   */
  def connectForm[S <: Session](webpage: Webpage[S]) = {
    webpage.live[FormField] {
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
    webpage.live[tag.Button] {
      case b => if (b.clickEvent() == null) b.clickEvent := RealtimeEvent(preventDefault = false)
    }
    webpage.live[tag.Form] {
      case f => {
        if (f.submitEvent() == null) f.submitEvent := RealtimeEvent()
      }
    }
  }

  /**
   * Sends all form data over realtime upon form submit.
   */
  def connectPost[S <: Session](webpage: Webpage[S]) = {
    webpage.live[tag.Form] {
      case f => if (f.submitEvent() == null) f.submitEvent := RealtimeEvent(fireChange = true)
    }
  }
}

case class JavaScriptMessage(instruction: String, content: Option[String] = None, selector: Option[String] = None, delay: Int = 0)

object JavaScriptMessage {
  implicit def JavaScriptMessageCodecJson: CodecJson[JavaScriptMessage] = casecodec4(JavaScriptMessage.apply, JavaScriptMessage.unapply)("instruction", "content", "selector", "delay")
}