package io.youi.gui.event

import io.youi.gui.Component

class EventSupport {
  this: Component =>

  lazy val event: Events = new Events(this)
}