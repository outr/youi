package org.hyperscala

import argonaut.Argonaut._
import argonaut.{Json, CodecJson}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait TagMessage {
  def id: String
}

case class JavaScriptTagMessage(id: String) extends TagMessage

object JavaScriptTagMessage {
  implicit def JavaScriptTagMessageCodecJson: CodecJson[JavaScriptTagMessage] = casecodec1(JavaScriptTagMessage.apply, JavaScriptTagMessage.unapply)("id")
}

case class KeyEventTagMessage(id: String, eventType: String, altKey: Boolean, char: Int, ctrlKey: Boolean, key: Int, locale: String, location: Long, metaKey: Boolean, repeat: Boolean, shiftKey: Boolean) extends TagMessage

object KeyEventTagMessage {
  implicit def KeyEventTagMessageCodecJson: CodecJson[KeyEventTagMessage] = casecodec11(KeyEventTagMessage.apply, KeyEventTagMessage.unapply)("id", "eventType", "altKey", "char", "ctrlKey", "key", "locale", "location", "metaKey", "repeat", "shiftKey")
}

case class ChangeTagMessage(id: String, value: Option[Json]) extends TagMessage

object ChangeTagMessage {
  implicit def ChangeTagMessageCodecJson: CodecJson[ChangeTagMessage] = casecodec2(ChangeTagMessage.apply, ChangeTagMessage.unapply)("id", "value")
}