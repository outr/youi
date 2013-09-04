package org.hyperscala.realtime.dsl

import org.hyperscala.selector.{TagIdSelector, Selector}
import org.hyperscala.javascript.{JSFunction1, JavaScriptString, JavaScriptContent}
import org.hyperscala.event.KeyUpEvent
import org.hyperscala.realtime.Realtime
import org.hyperscala.{Message, Unique}
import org.hyperscala.web.site.Webpage

import org.hyperscala.html._
import org.hyperscala.css.attributes.Display

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryDSL {
  def apply(selector: Selector) = jQuerySelector(selector)

  def apply(selector: String) = jQuerySelector(Selector(selector))

  def apply(element: HTMLTag) = jQuerySelector(TagIdSelector(element))

  def callbackStore = Webpage().store.getOrSet("jquerydsl_callbackstore", new CallbackStore)
}

class CallbackStore extends tag.Div(id = "jquerydsl_callbackstore") {
  private var map = Map.empty[String, () => Unit]

  // No visible display
  style.display := Display.None

  // Add it to the body of the web page
  Webpage().body.contents += this

  def createCallback(f: () => Unit) = synchronized {
    val id = Unique()
    map += id -> f
    JavaScriptString(s"communicator.send('call', 'jquerydsl_callbackstore', { id: '$id' });")
  }

  override def receive(event: String, message: Message) = event match {
    case "call" => map.get(message[String]("id")) match {
      case Some(f) => f()
      case None => warn(s"Unable to find match for call: $message")
    }
    case _ => super.receive(event, message)
  }
}

case class jQuerySelector(selector: Selector) {
  lazy val select = s"$$('${selector.value}')"

  def keyup(f: JSFunction1[KeyUpEvent, Boolean]) = {
    val instruction = s"$select.keyup(${f.content});"
    Realtime.sendJavaScript(instruction, onlyRealtime = false)
    this
  }

  def value() = JavaScriptString(s"$select.val()")

  def value(v: Any) = JavaScriptString(s"$select.val(${JavaScriptContent.toJS(v)})")
}