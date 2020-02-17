package io.youi.gui.event

import io.youi.gui.Component

trait GestureSupport extends EventSupport {
  this: Component =>

  lazy val gestures: Gestures = new Gestures(this, event)
}
