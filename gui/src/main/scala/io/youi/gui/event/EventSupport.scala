package io.youi.gui.event

import io.youi.gui.Component

import org.scalajs.{dom => jsdom}
import scala.scalajs.js

class EventSupport {
  this: Component =>

  lazy val event: Events = new Events(this)
}

object EventSupport {
  lazy val hasPointerSupport: Boolean = js.typeOf(js.Dynamic.global.PointerEvent) != "undefined"
  lazy val hasTouchSupport: Boolean = js.typeOf(jsdom.document.documentElement.asInstanceOf[js.Dynamic].ontouchstart) != "undefined"
}