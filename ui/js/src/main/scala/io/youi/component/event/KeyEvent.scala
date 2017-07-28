package io.youi.component.event

import io.youi.Key
import io.youi.component.Component
import org.scalajs.dom.raw.KeyboardEvent

case class KeyEvent(component: Component, key: Key, repeat: Boolean, originalEvent: KeyboardEvent) {

}

object KeyEvent {
//  def apply(component: Component, event: KeyboardEvent): KeyEvent = {
//    val key = event.keyCode
//  }
}