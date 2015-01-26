package org.hyperscala.realtime.dsl

import org.hyperscala.event.ClientEvent
import org.hyperscala.html._
import org.hyperscala.css.attributes.Display
import org.hyperscala.web.Webpage
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.Unique
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CallbackStore[S <: Session](webpage: Webpage[S]) extends tag.Div(id = "jquerydsl_callbackstore") {
  private var map = Map.empty[String, () => Unit]

  // No visible display
  style.display := Display.None

  // Add it to the body of the web page
  webpage.body.contents += this

  def createCallback(f: () => Unit) = synchronized {
    val id = Unique()
    map += id -> f
    JavaScriptString(s"realtimeSend('jquerydsl_callbackstore', 'call', { callbackId: '$id' });")
  }

  handle[CallbackEvent] {
    case evt => map.get(evt.callbackId) match {
      case Some(f) => f()
      case None => warn(s"Unable to find match for call: ${evt.callbackId}.")
    }
  }
}

case class CallbackEvent(callbackId: String) extends ClientEvent

object CallbackStore {
  def apply[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet("jquerydsl_callbackstore", new CallbackStore(webpage))
}