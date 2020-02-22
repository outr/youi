package io.youi.event

import io.youi.component.Component
import org.scalajs.{dom => jsdom}

import scala.scalajs.js

trait EventSupport {
  this: Component =>

  lazy val event: Events = new Events(this)
}

object EventSupport {
  lazy val hasPointerSupport: Boolean = js.typeOf(js.Dynamic.global.PointerEvent) != "undefined"
  lazy val hasTouchSupport: Boolean = js.typeOf(jsdom.document.documentElement.asInstanceOf[js.Dynamic].ontouchstart) != "undefined"
}