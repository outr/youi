package org.hyperscala.realtime.dsl

import org.hyperscala.html.tag
import org.hyperscala.css.attributes.Display
import org.hyperscala.web.Webpage
import org.hyperscala.{ResponseMessage, Unique}
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CallbackStore extends tag.Div(id = "jquerydsl_callbackstore") {
  private var map = Map.empty[String, () => Unit]

  // No visible display
  style.display := Display.None

  // Add it to the body of the web page
  Webpage().body.contents += this

  def createCallback(f: () => Unit) = synchronized {
    val id = Unique()
    map += id -> f
    JavaScriptString(s"realtimeSend('jquerydsl_callbackstore', 'call', { callbackId: '$id' });")
  }

  override def receive(event: String, message: ResponseMessage) = event match {
    case "call" => map.get(message[String]("callbackId")) match {
      case Some(f) => f()
      case None => warn(s"Unable to find match for call: $message")
    }
    case _ => super.receive(event, message)
  }
}

object CallbackStore {
  def apply() = Webpage().store.getOrSet("jquerydsl_callbackstore", new CallbackStore)
}