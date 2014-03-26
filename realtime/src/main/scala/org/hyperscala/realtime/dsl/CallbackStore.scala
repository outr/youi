package org.hyperscala.realtime.dsl

import org.hyperscala.html._
import org.hyperscala.css.attributes.Display
import org.hyperscala.web.Webpage
import org.hyperscala.TagMessage
import argonaut.{JsonObject, CodecJson}
import argonaut.Argonaut._
import org.hyperscala.javascript.JavaScriptString
import scala.Some
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

  override def receive(event: String, json: JsonObject) = event match {
    case "call" => map.get(json.string("callbackId")) match {
      case Some(f) => f()
      case None => warn(s"Unable to find match for call: $json")
    }
    case _ => super.receive(event, json)
  }
}

object CallbackStore {
  def apply[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet("jquerydsl_callbackstore", new CallbackStore(webpage))
}

case class CallbackTagMessage(id: String, callbackId: String) extends TagMessage

object CallbackTagMessage {
  implicit def CallbackTagMessageCodecJson: CodecJson[CallbackTagMessage] = casecodec2(CallbackTagMessage.apply, CallbackTagMessage.unapply)("id", "callbackId")
}